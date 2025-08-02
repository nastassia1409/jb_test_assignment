package models;

import java.util.List;

public class TransferRequest {
    public List<String> licenseIds;
    public int targetTeamId;

    public TransferRequest(List<String> licenseIds, int targetTeamId) {
        this.licenseIds = licenseIds;
        this.targetTeamId = targetTeamId;
    }
}
