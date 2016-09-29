package com.edvantis.workopolis.dao.interviewer;

import java.util.List;
import java.util.UUID;

import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.User;

public interface InterviewerDAO {
	
	public List<Interviewer> getInterviewers();
	
	public Interviewer getInterviewerById(int id);
	
	public void addInterviewer(Interviewer interviewer);
	
	public void deteteInterviewer(int id);
	
	public Interviewer getInterviewerByEmail(String email);
	
	public void update(Interviewer interviewer);
	
	//public void register(Interviewer account, UUID uuid);
	
	public void saveUUID(String uuid, int userId);
	
	public void confirm(String uuid, String email);

}
