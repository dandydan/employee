package com.dandy.core;

import org.hibernate.Session;

public class CommandInvoker{

	DbCommand theCommand;
	
	public CommandInvoker(DbCommand newCommand){
		theCommand = newCommand;
	}
	
	public void invoke(Session session){
		theCommand.execute(session);
	}
	
}
