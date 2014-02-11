
###### AFTER DATA COLLECTION  AND PREPARATION ######
base="/media/old/jarthur/workspace-msr/msr-challenge/data/discussions"

# classify
#python classify.py ../discussions-files.txt


#                RQ1                        #
# generate raw-project-activity-category.data
cd $base/categorized
awk -F' ' '{print $2 " " $3 " " $NF}' * > ../design/data-to-plot/raw-project-activity-category.data

cd $base/design/plots
python generate-project-design-general-proportion.py > ../data-to-plot/to-plot-project-design-general-proportion.data
Rscript plot-box-plot-design-per-general.R


#                RQ2                        #
cd $base/categorized
awk -F' ' '{print $1 " " $3 " " $NF " " $2}' * > ../design/data-to-plot/raw-developer-activity-category-project.data
cd $base/design/plots
python generate-project-designers-commiters-proportion.py > ../data-to-plot/to-plot-project-designers-commiters-proportion.data

# Desingers proportion boxplot
Rscript plot-box-plot-designers-per-project.R

# generate raw-developer-project-designactivity
cd $base/categorized
grep 'design $' * | awk -F' ' '{print $1 " " $2 " " $3}' | cut -d':' -f2 > ../design/data-to-plot/raw-developer-project-design_activity.data

cd $base/design/plots
python generate-project-developer-freq-amp.py > ../data-to-plot/to-plot-project-developer-intensity-amplitude.data
Rscript plot-designers.R

cp ../graphs/* /media/old/jarthur/Dropbox/doutorado/paper-msr-challenge-2014/graphs/


