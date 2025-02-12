import requests
import os
import re
import time

# Set your GitHub Personal Access Token
GITHUB_TOKEN = os.getenv("GITHUB_TOKEN", "your_actual_token_here")  

# GitHub API Headers
HEADERS = {
    "Authorization": f"token {GITHUB_TOKEN}",
    "Accept": "application/vnd.github.v3+json"
}

def search_code_files(query, extension, per_page=100, max_results=1000):
    """
    Searches GitHub for code files with a specific extension, handling pagination and API limits.
    """
    total_files = []
    page = 1

    while len(total_files) < max_results:
        url = f"https://api.github.com/search/code?q={query}+extension:{extension}&sort=indexed&order=desc&per_page={per_page}&page={page}"
        response = requests.get(url, headers=HEADERS)

        if response.status_code == 200:
            items = response.json().get("items", [])
            if not items:
                break  # Stop if no more results
            total_files.extend(items)
            if len(items) < per_page:
                break  # Stop if fewer results returned than requested
        elif response.status_code == 403:  # Handle rate limit
            retry_wait_time = min(60 * (page // 2), 300)  # Exponential backoff (max 5 minutes)
            print(f"⚠️ GitHub API rate limit exceeded! Retrying in {retry_wait_time} seconds...")
            time.sleep(retry_wait_time)
            continue
        else:
            print(f"❌ Error: {response.status_code}, {response.text}")
            break

        page += 1

    return total_files[:max_results]  # Return only the required number of results

def fetch_code_from_github(file_url):
    """
    Fetches raw code content from a GitHub raw file URL.
    """
    response = requests.get(file_url, headers=HEADERS)
    return response.text if response.status_code == 200 else None

def count_lambda_usage(code_content):
    """
    Counts lambda expressions (->) in the code.
    """
    return len(re.findall(r"->", code_content))

def main():
    search_query = "lambda"
    extensions = ["kt", "swift"]  # Kotlin and Swift file extensions

    total_repos_crawled = set()  # Unique repositories crawled
    repos_with_lambda = set()  # Unique repositories that contain at least one lambda
    total_lambda_expressions = 0  # Total lambda expressions found
    total_kt_files = 0  # Total Kotlin files crawled
    total_swift_files = 0  # Total Swift files crawled

    for ext in extensions:
        print(f"\n🔍 Searching for *.{ext} files related to '{search_query}' ...\n")
        files = search_code_files(search_query, ext, per_page=100, max_results=1000)  # Fetch up to 1000 files

        if not files:
            print(f"❌ No {ext} files found.")
            continue

        for file in files:
            repo_name = file["repository"]["full_name"]
            raw_url = file["html_url"].replace("github.com", "raw.githubusercontent.com").replace("/blob/", "/")

            total_repos_crawled.add(repo_name)  # Track unique repositories

            # Track file type counts
            if ext == "kt":
                total_kt_files += 1
            elif ext == "swift":
                total_swift_files += 1

            code_content = fetch_code_from_github(raw_url)
            if code_content:
                lambda_count = count_lambda_usage(code_content)
                total_lambda_expressions += lambda_count

                if lambda_count > 0:
                    repos_with_lambda.add(repo_name)  # Ensure unique repo count

    # Display Summary
    print("\n🔎 **Summary Report** 🔎")
    print(f"📌 Total Unique Repositories Crawled: {len(total_repos_crawled)}")
    print(f"📂 Total Kotlin (.kt) Files Crawled: {total_kt_files}")
    print(f"📂 Total Swift (.swift) Files Crawled: {total_swift_files}")
    print(f"✅ Unique Repositories Using Lambda: {len(repos_with_lambda)} ({(len(repos_with_lambda)/len(total_repos_crawled)*100) if total_repos_crawled else 0:.2f}%)")
    print(f"🔢 Total Lambda Expressions Found: {total_lambda_expressions}")

if __name__ == "__main__":
    main()
