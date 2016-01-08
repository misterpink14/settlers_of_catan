package server.databaseAccess;

import java.sql.SQLException;
import java.util.ArrayList;

import server.database.DatabaseRepresentation;
import shared.models.Batch;

public class BatchDAO extends DatabaseAccessObject
{
	/**
	 * This class contains all Database Access methods necessary for the Batch class.
	 */
	public BatchDAO(DatabaseRepresentation db)
	{
		super(db);
	}
	
	/**
	 * Submits a new batch to the database
	 * @param batchID
	 * @param fieldValue
	 * @param recordValue
	 * @return success or failure
	 * @throws SQLException 
	 */
	public String addBatch(Batch batch) throws SQLException
	{
		ArrayList<Object> values = new ArrayList<Object>();
		
		values.add(batch.getProjectID());
		values.add(batch.isInUse());
		values.add(batch.isCompleted());
		values.add(batch.getSourceURL());
		
		String batchID = add("Batch", "ProjectID, InUse, Completed, SourceURL", values);
		return batchID;
	}
	
	/**
	 * gets a sample batch from the database
	 * @param projectID
	 * @return the sample batch
	 * @throws SQLException 
	 */
	public Batch getSampleBatch(String projectID) throws SQLException
	{
		ArrayList<Object> results = get("Batch", "ProjectID = \"" + projectID +"\"");
		if(results.isEmpty())
		{
			return null;
		}
		
		Batch result = new Batch(String.valueOf((Integer) results.get(0)), String.valueOf((Integer) results.get(1)), (Integer) results.get(2), 
				(Integer) results.get(3), (String) results.get(4));
		
		return result;
	}
	
	
	/**
	 * Gets a batch by ID
	 * @param batchID
	 * @return Batch
	 * @throws SQLException
	 */
	public Batch getBatch(String batchID) throws SQLException
	{
		return null;
	}		
	
	/**
	 * Updates the information within a batch
	 * @param batch
	 * @return whether or not the update was a success
	 * @throws SQLException
	 */
	public Boolean updateBatch(Batch batch) throws SQLException
	{
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(batch.isInUse());
		values.add(batch.isCompleted());
		
		ArrayList<String> tableValues = new ArrayList<String>();
		tableValues.add("InUse");
		tableValues.add("Completed");
		
		String whereStatement = "batchID = \"" + batch.getBatchID() + "\"";
		Boolean success = update("Batch", whereStatement, tableValues, values);
		return success;
	}
}
