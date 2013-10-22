## issue comments##

# comments per issue
cut -d' ' -f2 project-issue-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > comments-per-issue.freq

# developers per issue
cut -d' ' -f2,4 project-issue-comment-developer.data | sort | uniq -c | sort -rn | awk -F' ' '{pnt $2}' |  sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > developers-per-issue.freq

# issue comment per developer
cut -d' ' -f4 project-issue-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > issuecomments-per-developer.freq

# issue per developer
cut -d' ' -f2,4 project-issue-comment-developer.data | sort | uniq -c | awk -F' ' '{print $NF}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > issue-per-developer.freq

# issue per project
cut -d' ' -f1,2 project-issue-comment-developer.data | sort | uniq -c | awk -F' ' '{print $2}' | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > issues-per-proj.freq

# issue comment per project
cut -d' ' -f1 project-issue-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' >  issuecomments-per-proj.freq

## commit comments ##

# commit comments per project
cut -d' ' -f1 project-commit-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g'> commit-comments-per-project.freq

# comments per commit
cut -d' ' -f2 project-commit-comment-developer.data | sort | uniq -c | sort -rn | sed 's/^[ ]*//g' > comments-per-commit.freq

# commenter per commit
cut -d' ' -f2,4 project-commit-comment-developer.data | sort | uniq -c | awk -F' ' '{print $(NF-1)}' | sort | uniq -c | sort -rn | sed 's/^[ [ ]*//g' > commenter-per-commit.freq

