library("ggplot2")
theme_set(theme_bw())
data = read.table("../data-to-plot/to-plot-project-developer-intensity-amplitude.data", header = T)

to = ggplot(data, aes(y = Coverage, x = Project))

to = to + geom_jitter(colour = "blue") + theme(axis.text.y = element_blank(), axis.ticks=element_blank()) + scale_y_discrete(breaks = seq(10,100,10)) + coord_flip() + ylab("Coverage (%)") + xlab("Projects")

ggsave(to, file="../graphs/designers-amplitude-rq2.pdf", width=4, height=4)

