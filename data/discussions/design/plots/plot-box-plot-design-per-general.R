library("ggplot2")
#theme_set(theme_bw())

data = read.table("../data-to-plot/to-plot-project-design-general-proportion.data", header = T)

gg = ggplot(data, aes(x = factor(0), y = Proportion))

p = gg + geom_boxplot() + theme( axis.title.x=element_blank(),axis.text.x=element_blank(),axis.ticks.x=element_blank() ) + coord_cartesian(ylim=c(0, 1)) + ylab("Design discussions proportion") + geom_jitter(shape = 1) + scale_y_continuous(breaks=seq(0,1,1/10))
ggsave(p, file="../graphs/boxplot-design-proportion-rq1.pdf", width=4, height=4)





#  Proportion     
# Min.   :0.08929  
# 1st Qu.:0.20930  
# Median :0.25000  
# Mean   :0.24598  
# 3rd Qu.:0.29379  
# Max.   :0.35794 
# sd: 0.06219354
