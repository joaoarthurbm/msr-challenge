library("ggplot2")
theme_set(theme_bw())
data = read.table("../data-to-plot/to-plot-projeto-desenvolvedor-intensidade-amplitude.data", header = T)

to = ggplot(data, aes(y = Amplitude, x = Project))

to + geom_jitter(colour = "blue") + theme(axis.text.x=element_blank(), axis.ticks=element_blank(), axis.title.y=element_blank()) + scale_y_discrete(breaks = seq(10,100,10))


