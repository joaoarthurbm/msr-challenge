library("ggplot2")
theme_set(theme_bw())

data = read.table("projeto-desenvolvedor-intensidade-amplitude.data", header = T)
to = ggplot(data, aes(x = sort(Amplitude), group = Project))
to + stat_ecdf(size = 1, alpha = 1/3) + theme(legend.position="none") + ylab("Cumulative Frequency") + xlab("Amplitude (%)")
