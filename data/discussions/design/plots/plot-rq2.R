library("ggplot2")
theme_set(theme_bw())
data = read.table("project-architects-developers.data", header = T)
to = ggplot(data, aes(Projects, fill = Role))
to + geom_bar(position = "fill") + scale_y_continuous(labels = percent_format()) + scale_fill_grey() + theme(axis.text.x=element_blank(),  axis.ticks=element_blank(), axis.title.y=element_blank())
