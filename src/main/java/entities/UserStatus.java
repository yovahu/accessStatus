package entities;

public class UserStatus extends User{
    private boolean status;
    private String blockDate;

    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus(){
        return status;
    }

    public void setBlockDate(String blockDate) {
        this.blockDate = blockDate;
    }

    public String getBlockDate() {
        return blockDate;
    }

}