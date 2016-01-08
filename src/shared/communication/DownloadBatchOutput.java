package shared.communication;

import java.util.ArrayList;

public class DownloadBatchOutput 
{

	/**
	 * The batch id of the output
	 */
	private String BATCH_ID;
	/**
	 * The project id of the output
	 */
	private String	PROJECT_ID;
	/**
	 * The url of the image of the output
	 */
	private String IMAGE_URL;
	/**
	 * The The y coordinate of the first field of the output batch
	 */
	private String FIRST_Y_COORD;
	/**
	 * The height of each field in the output batch
	 */
	private String RECORD_HEIGHT;
	/**
	 * The number of records in the output batch
	 */
	private String NUM_RECORDS;
	/**
	 * The number of fields in the output batch
	 */
	private Integer NUM_FIELDS;

	public DownloadBatchOutput() 
	{}
	
	/**
	 * This object stores the output of the DownloadBatch function of the client communicator
	 * @param batchID
	 * @param projectID
	 * @param imageURL
	 * @param firstY
	 * @param height
	 * @param records
	 * @param numFields
	 * @param fields
	 * @param success
	 */
	public DownloadBatchOutput(String batchID, String projectID, String imageURL, String firstY, String height, String records, Integer numFields) 
	{
		this.setBATCH_ID(batchID);
		this.setPROJECT_ID(projectID);
		this.setIMAGE_URL(imageURL);
		this.setFIRST_Y_COORD(firstY);
		this.setRECORD_HEIGHT(height);
		this.setNUM_RECORDS(records);
		this.setNUM_FIELDS(numFields);
	}

	/**
	 * @return the bATCH_ID
	 */
	public String getBATCH_ID()
	{
		return BATCH_ID;
	}

	/**
	 * @param bATCH_ID the bATCH_ID to set
	 */
	public void setBATCH_ID(String bATCH_ID)
	{
		BATCH_ID = bATCH_ID;
	}

	/**
	 * @return the pROJECT_ID
	 */
	public String getPROJECT_ID()
	{
		return PROJECT_ID;
	}

	/**
	 * @param pROJECT_ID the pROJECT_ID to set
	 */
	public void setPROJECT_ID(String pROJECT_ID)
	{
		PROJECT_ID = pROJECT_ID;
	}

	/**
	 * @return the iMAGE_URL
	 */
	public String getIMAGE_URL()
	{
		return IMAGE_URL;
	}

	/**
	 * @param iMAGE_URL the iMAGE_URL to set
	 */
	public void setIMAGE_URL(String iMAGE_URL)
	{
		IMAGE_URL = iMAGE_URL;
	}

	/**
	 * @return the fIRST_Y_COORD
	 */
	public String getFIRST_Y_COORD()
	{
		return FIRST_Y_COORD;
	}

	/**
	 * @param fIRST_Y_COORD the fIRST_Y_COORD to set
	 */
	public void setFIRST_Y_COORD(String fIRST_Y_COORD)
	{
		FIRST_Y_COORD = fIRST_Y_COORD;
	}

	/**
	 * @return the rECORD_HEIGHT
	 */
	public String getRECORD_HEIGHT()
	{
		return RECORD_HEIGHT;
	}

	/**
	 * @param rECORD_HEIGHT the rECORD_HEIGHT to set
	 */
	public void setRECORD_HEIGHT(String rECORD_HEIGHT)
	{
		RECORD_HEIGHT = rECORD_HEIGHT;
	}

	/**
	 * @return the nUM_RECORDS
	 */
	public String getNUM_RECORDS()
	{
		return NUM_RECORDS;
	}

	/**
	 * @param nUM_RECORDS the nUM_RECORDS to set
	 */
	public void setNUM_RECORDS(String nUM_RECORDS)
	{
		NUM_RECORDS = nUM_RECORDS;
	}

	/**
	 * @return the nUM_FIELDS
	 */
	public Integer getNUM_FIELDS()
	{
		return NUM_FIELDS;
	}

	/**
	 * @param nUM_FIELDS the nUM_FIELDS to set
	 */
	public void setNUM_FIELDS(Integer nUM_FIELDS)
	{
		NUM_FIELDS = nUM_FIELDS;
	}
}
