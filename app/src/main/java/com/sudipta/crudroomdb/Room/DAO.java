package com.sudipta.crudroomdb.Room;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DAO {       //DATA ACCESS OBJECT

    @Insert
    public void studentInsertion(Student student);


}
