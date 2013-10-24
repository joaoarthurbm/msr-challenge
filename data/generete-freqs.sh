## issue comments ##

# comments per issue commented
cut -d' ' -f2 project-issue-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-comments-per-issue.freq

# commenter per issue
cut -d' ' -f2,4 project-issue-comment-developer.data | sort | uniq -c | sort -rn | awk -F' ' '{print $2}' |  sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-commenter-per-issue.freq

# issue comment per developer
cut -d' ' -f4 project-issue-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-issuecomments-per-developer.freq

# issue per developer
cut -d' ' -f2,4 project-issue-comment-developer.data | sort | uniq -c | awk -F' ' '{print $NF}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-issue-per-developer.freq

# issue per project
cut -d' ' -f1,2 project-issue-comment-developer.data | sort | uniq -c | awk -F' ' '{print $2}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-issue-per-proj.freq

# issue comment per project
cut -d' ' -f1 project-issue-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' >  com-issuecomments-per-proj.freq

## commit comments ##

# commit comments per project
cut -d' ' -f1 project-commit-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g'> com-commit-comments-per-project.freq

# comments per commit
cut -d' ' -f2 project-commit-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-comments-per-commit.freq

# comments per developer
cut -d' ' -f4 project-commit-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-commit-comments-per-developer.freq

# commenter per commit
cut -d' ' -f2,4 project-commit-comment-developer.data | sort | uniq -c | awk -F' ' '{print $(NF-1)}' | sort | uniq -c | sort -rn | sed 's/^[ [ ]*//g' > com-commenter-per-commit.freq

# commits per project
cut -d' ' -f1,2 project-commit-comment-developer.data | sort | uniq -c | awk -F' ' '{print $2}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-commit-per-proj.freq

# commits per developer
cut -d' ' -f2,4 project-commit-comment-developer.data | sort | uniq -c | awk -F' ' '{print $NF}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-commit-per-developer.freq

## Pull request comments ##

# commenter per pull
cut -d' ' -f2,5 project-pull-comment-commit-developer.data | sort | uniq -c | awk -F' ' '{print $(NF-1)}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-commenter-per-pull.freq

# comments per pull
cut -d' ' -f2 project-pull-comment-commit-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-comments-per-pull.freq

# pull comments per project
cut -d' ' -f1 project-pull-comment-commit-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-pull-comments-per-project.freq

# pull comments per developer
cut -d' ' -f5 project-pull-comment-commit-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-pull-comments-per-developer.freq

# pull per developer
cut -d' ' -f2,5 project-pull-comment-commit-developer.data | sort | uniq -c | awk -F' ' '{print $NF}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-pull-per-developer.freq

# pull per project
cut -d' ' -f1,2 project-pull-comment-commit-developer.data | sort | uniq -c | awk -F' ' '{print $2}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > com-pull-per-project.freq

## General stats
# developer per project
cut -d' ' -f1 project-collaborator.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > all-developers-per-project.freq
