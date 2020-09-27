package entities;

//UsersBanList
public class UserStatus {
    private int user_id;
    private boolean status;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }


}
