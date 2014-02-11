library("ggplot2")
data = read.table("../data-to-plot/to-plot-project-developer-commits-amplitude.data", header = T)
to = ggplot(data, aes(x=Amplitude, y=Commits))
p = to+geom_jitter() + stat_smooth() + ylab("Commits (%)") + xlab("Amplitude %")
ggsave(p, file="../graphs/to-plot-project-developer-commits-amplitude.pdf", width=4, height=4)


