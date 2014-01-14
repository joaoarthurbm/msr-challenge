data = read.table("freq-proj-category.data", header = T)
c = data$Design / (data$General+data$Design)
toPlot = as.data.frame(c)
gg = ggplot(toPlot, aes(x = factor(0), y = toPlot$c))
gg + geom_boxplot() + theme(axis.title.x=element_blank(),axis.text.x=element_blank(),axis.ticks.x=element_blank()) + coord_cartesian(ylim=c(0, 1)) +
ylab("Design discussions proportion")
