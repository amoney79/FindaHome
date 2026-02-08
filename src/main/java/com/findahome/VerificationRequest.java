package com.findahome;

public class VerificationRequest {
    private int id;
    private int userId;
    private String requesterName;
    private String requestType; // AGENT, LANDLORD, PROPERTY
    private String status; // PENDING, APPROVED, REJECTED
    private String dateRequested;
    private String details;

    public VerificationRequest(int userId, String requesterName, String requestType, String status,
            String dateRequested, String details) {
        this.userId = userId;
        this.requesterName = requesterName;
        this.requestType = requestType;
        this.status = status;
        this.dateRequested = dateRequested;
        this.details = details;
    }

    public VerificationRequest(int id, int userId, String requesterName, String requestType, String status,
            String dateRequested, String details) {
        this(userId, requesterName, requestType, status, dateRequested, details);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getStatus() {
        return status;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public String getDetails() {
        return details;
    }
}
