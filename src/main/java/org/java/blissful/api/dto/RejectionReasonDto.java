package org.java.blissful.api.dto;

public class RejectionReasonDto {
	
	private String rejectionReason;
	
	public RejectionReasonDto() {}
	
	public RejectionReasonDto(String rejectionReason) {
		setRejectionReason(rejectionReason);
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	

}
