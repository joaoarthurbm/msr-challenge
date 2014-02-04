#rm /media/old/jarthur/workspace-msr/msr-challenge/data/discussions/uncategorized/*
#cd /media/old/jarthur/workspace-msr/msr-challenge
#java -cp bin:lib/mongo-java-driver-2.9.3.jar main.Main
#cd /home/jarthur/workspace-msr/msr-challenge/data/discussions/uncategorized/
#sed -i 's/  */\ /g' *
#find ./ -name "*.data" -size 0 -exec rm {} \;
#for file in `ls -1`; do echo `pwd`/$file; done > ~/workspace-msr/msr-challenge/data/discussions/discussions-files.txt
#cd /home/jarthur/workspace-msr/msr-challenge/data/discussions/design
#python classify.py ../discussions-files.txt
