package com.edvantis.workopolis.dao.result;



import com.edvantis.workopolis.model.interview.InterviewResult;

public interface ResultDAO {


	public InterviewResult gerResultById(int id);

	public void updateOrCreateResult(InterviewResult result);

}
