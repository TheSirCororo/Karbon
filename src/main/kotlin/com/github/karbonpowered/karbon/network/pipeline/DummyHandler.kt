package com.github.karbonpowered.karbon.network.pipeline

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerAdapter

@ChannelHandler.Sharable
object DummyHandler : ChannelHandlerAdapter()