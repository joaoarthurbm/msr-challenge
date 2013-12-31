c = data$Design / (data$General+data$Design)
toPlot = as.data.frame(c)
gg = ggplot(toPlot, aes(x = factor(0), y = toPlot$c))
gg + geom_boxplot() + theme(axis.title.x=element_blank(),axis.text.x=element_blank(),axis.ticks.x=element_blank())
