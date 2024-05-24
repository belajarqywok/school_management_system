package com.SchoolManagementSystem.Repositories;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import com.SchoolManagementSystem.Dtos.RoomDataDto;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;

public class RoomRepositories extends DatabaseConfigurations {

  // Table Name
  private final String TABLENAME = "rooms";

  /**
   * Get Rooms Repository
   *
   * @return List<RoomDataDto>
   *
   */
  public List<RoomDataDto> getRoomsRepository() {
    List<RoomDataDto> roomsDataList = new ArrayList<>();
    Connection connection = hikariConfiguration();
    String queryString = String.format(
      "SELECT * FROM %s", this.TABLENAME
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        RoomDataDto room = new RoomDataDto();
        
        room.setRoomCode(resultSet
            .getString("room_code"));
        room.setRoomName(resultSet
            .getString("room_name"));
        room.setRoomLocation(resultSet
            .getString("room_location"));
        room.setRoomWide(resultSet
            .getInt("room_wide"));
        room.setRoomCapacity(resultSet
            .getInt("room_capacity"));
        room.setRoomPrice(resultSet
            .getInt("room_price"));
        room.setRoomNote(resultSet
            .getString("room_note"));

        roomsDataList.add(room);
      }

      return roomsDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return roomsDataList;
    }
  }

  /**
   * Create Room Repository
   *
   * @param dto RoomDataDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> createRoomRepository(RoomDataDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = hikariConfiguration();
    String queryString = String.format(
      "INSERT INTO %s ( " +
        "room_name,    "     +
        "room_location, "     +
        "room_wide, "     +
        "room_capacity, "     +
        "room_price, "     +
        "room_note    "     +
      ")"                        +
      "VALUES (?, ?, ?, ?, ?, ?)", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, dto.getRoomName());
      statement.setString(2, dto.getRoomLocation());
      statement.setDouble(3, dto.getRoomWide());
      statement.setInt(4, dto.getRoomCapacity());
      statement.setDouble(5, dto.getRoomPrice());
      statement.setString(6, dto.getRoomNote());

      if (statement.executeUpdate() > 0) {
        connection.commit();

        response.put("result", true);
        response.put("message", "Data Berhasil Ditambahkan.");

        return response;

      } else {
        connection.rollback();

        response.put("result", false);
        response.put("message", "Data Gagal Ditambahkan, Coba Lagi.");

        return response;
      }

    } catch (SQLIntegrityConstraintViolationException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }

      response.put("result", false);
      response.put(
        "message", 
        "Data gagal ditambahkan karena duplikasi ID atau kesalahan integritas data."
      );

      return response;

    } catch (SQLException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }

      response.put("result", false);
      response.put("message", "Data Gagal Ditambahkan, Coba Lagi.");

      return response;

    } finally {
      try { connection.setAutoCommit(true); }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }

  /**
   * Update Room Repository
   *
   * @param dto RoomDataDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> updateRoomRepository(RoomDataDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = hikariConfiguration();
    String queryString = String.format(
      "UPDATE %s SET "       +
        "room_name        = ?, "  +
        "room_location    = ?, "  +
        "room_wide        = ?, "  +
        "room_capacity    = ?, "  +
        "room_price       = ?, "  +
        "room_note        = ?  "  +
      "WHERE room_code    = ?  ", 
      this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, dto.getRoomName());
      statement.setString(2, dto.getRoomLocation());
      statement.setInt(3, dto.getRoomWide());
      statement.setInt(4, dto.getRoomCapacity());
      statement.setInt(5, dto.getRoomPrice());
      statement.setString(6, dto.getRoomNote());
      statement.setString(7, dto.getRoomCode());

      if (statement.executeUpdate() > 0) {
        connection.commit();

        response.put("result", true);
        response.put("message", "Data Berhasil Diperbarui.");

        return response;

      } else {
        connection.rollback();

        response.put("result", false);
        response.put("message", "Data Gagal Diperbarui, Coba Lagi.");

        return response;
      }

    } catch (SQLException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }

      response.put("result", false);
      response.put("message", "Data Gagal Diperbarui, Coba Lagi.");

      return response;

    } finally {
      try { connection.setAutoCommit(true); }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }

  /**
   * Delete Room Repository
   *
   * @param roomId String
   * @return Boolean
   *
   */
  public boolean deleteRoomRepository(String roomId) {
    Connection connection = hikariConfiguration();
    String queryString = String.format(
      "DELETE FROM %s WHERE room_code = ?", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, roomId);

      if (statement.executeUpdate() > 0) {
        connection.commit();
        return true;

      } else {
        connection.rollback();
        return false;
      }

    } catch (SQLException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }

      return false;

    } finally {
      try {
        connection.setAutoCommit(true);
      }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }

  /**
   * Search Rooms Repository
   *
   * @param key String
   * @return List<RoomDataDto>
   *
   */
  public List<RoomDataDto> searchRoomsRepository(String key) {
    List<RoomDataDto> roomsDataList = new ArrayList<>();
    Connection connection = hikariConfiguration();
    String queryString = String.format(
      "SELECT * FROM %s WHERE (  " +
        "room_code      LIKE '%%%s%%' OR " +
        "room_name      LIKE '%%%s%%' OR " +
        "room_location  LIKE '%%%s%%' OR " +
        "room_note      LIKE '%%%s%%' OR " +
        "room_price     LIKE '%%%s%%' OR " +
        "room_capacity  LIKE '%%%s%%' OR " +
        "room_wide      LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key, key, key, key, key
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        RoomDataDto room = new RoomDataDto();
        room.setRoomCode(resultSet.getString("room_code"));
        room.setRoomName(resultSet.getString("room_name"));
        room.setRoomLocation(resultSet.getString("room_location"));
        room.setRoomWide(resultSet.getInt("room_wide"));
        room.setRoomCapacity(resultSet.getInt("room_capacity"));
        room.setRoomPrice(resultSet.getInt("room_price"));
        room.setRoomNote(resultSet.getString("room_note"));

        roomsDataList.add(room);
      }

      return roomsDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return roomsDataList;
    }
  }
}
