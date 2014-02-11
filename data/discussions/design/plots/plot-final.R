# Multiple plot function
#
# ggplot objects can be passed in ..., or to plotlist (as a list of ggplot objects)

# - cols:   Number of columns in layout
# - layout: A matrix specifying the layout. If present, 'cols' is ignored.
#
# If the layout is something like matrix(c(1,2,3,3), nrow=2, byrow=TRUE),
# then plot 1 will go in the upper left, 2 will go in the upper right, and
# 3 will go all the way across the bottom.
#
multiplot <- function(..., plotlist=NULL, file, cols=1, layout=NULL) {
  require(grid)

  # Make a list from the ... arguments and plotlist
  plots <- c(list(...), plotlist)

  numPlots = length(plots)

  # If layout is NULL, then use 'cols' to determine layout
  if (is.null(layout)) {
    # Make the panel
    # ncol: Number of columns of plots
    # nrow: Number of rows needed, calculated from # of cols
    layout <- matrix(seq(1, cols * ceiling(numPlots/cols)),
                    ncol = cols, nrow = ceiling(numPlots/cols))
  }

 if (numPlots==1) {
    print(plots[[1]])

  } else {
    # Set up the page
    grid.newpage()
    pushViewport(viewport(layout = grid.layout(nrow(layout), ncol(layout))))
	
        # Make each plot, in the correct location
    for (i in 1:numPlots) {
      # Get the i,j matrix positions of the regions that contain this subplot
      matchidx <- as.data.frame(which(layout == i, arr.ind = TRUE))

      print(plots[[i]], vp = viewport(layout.pos.row = matchidx$row,
                                      layout.pos.col = matchidx$col))
    }

  }

}


library("ggplot2")
theme_set(theme_bw())

data = read.table("../data-to-plot/to-plot-project-design-general-proportion.data", header = T)
gg = ggplot(data, aes(x = factor(0), y = Proportion))
p = gg + geom_boxplot() + theme( axis.title.x=element_blank(),axis.text.x=element_blank(),axis.ticks.x=element_blank() ) + coord_cartesian(ylim=c(0, 1)) + xlab("Design discussions proportion") + geom_jitter() + scale_y_continuous(breaks=seq(0,1,1/10))

data = read.table("../data-to-plot/to-plot-project-designers-commiters-proportion.data", header = T)
gg = ggplot(data, aes(x = factor(0), y = Proportion))
q = gg + geom_boxplot() + theme( axis.title.x=element_blank(),axis.text.x=element_blank(),axis.ticks.x=element_blank() ) + coord_cartesian(ylim=c(0, 1)) + xlab("Designers proportion") + geom_jitter() + scale_y_continuous(breaks=seq(0,1,1/10))

data = read.table("../data-to-plot/to-plot-project-developer-intensity-amplitude.data", header = T)
to = ggplot(data, aes(y = Amplitude, x = Project))
to = to + geom_jitter(colour = "blue") + theme(axis.text.y = element_blank(), axis.ticks=element_blank(), axis.title.y=element_blank()) + scale_y_discrete(breaks = seq(10,100,10)) + coord_flip()

multiplot(p, q, to , to, cols=4)




#ggsave("../graphs/all-plots-final.pdf", toSave, width=8.5, height=2.3)
