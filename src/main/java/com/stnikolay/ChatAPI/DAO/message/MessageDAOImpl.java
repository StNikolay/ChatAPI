package com.stnikolay.ChatAPI.DAO.message;

import com.stnikolay.ChatAPI.DAO.user.UserDAO;
import com.stnikolay.ChatAPI.model.Dialogue;
import com.stnikolay.ChatAPI.model.Message;
import com.stnikolay.ChatAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserDAO userDAO;

    @Override
    public Message sendMessage(Message message) {

        setRightIdsForUsers(message.getSender(), message.getReceiver());

        if (checkForExistence(message)) {
            jdbcTemplate.update(
                    "insert into t_message(sender, receiver, text, time, date) " +
                            "values(?, ?, ?, ?, ?)",
                    message.getSender().getId(),
                    message.getReceiver().getId(),
                    message.getText(),
                    message.getTime(),
                    message.getDate()
            );
        } else return null;

        message.setId(getMessageId(message));
        return message;
    }

    @Override
    public Dialogue getDialogueMessages(String user, String interlocutor, String date) {

        return new Dialogue(jdbcTemplate.query(
                "select * from t_message " +
                        "where ((sender = ? and receiver = ?) or (sender = ? and receiver = ?)) and date = ? " +
                        "order by time",
                new Object[] {
                        userDAO.findUserByUsername(user).getId(),
                        userDAO.findUserByUsername(interlocutor).getId(),
                        userDAO.findUserByUsername(interlocutor).getId(),
                        userDAO.findUserByUsername(user).getId(),
                        date
                },
                (rs, rowNum) -> {
                    Message message = new Message();
                    message.setId(rs.getLong("id"));
                    message.setSender(userDAO.findById(rs.getLong("sender")));
                    message.setReceiver(userDAO.findById(rs.getLong("receiver")));
                    message.setText(rs.getString("text"));
                    message.setTime(rs.getString("time"));
                    message.setDate(rs.getString("date"));
                    return message;
                }
        ));
    }

    private Long getMessageId(Message message) {
        return jdbcTemplate.queryForObject(
                "select id from t_message " +
                        "where sender = ? and receiver = ? and text = ? and time = ? and date = ?",
                Long.class,
                message.getSender().getId(),
                message.getReceiver().getId(),
                message.getText(),
                message.getTime(),
                message.getDate()
        );
    }

    private void setRightIdsForUsers(User user1, User user2) {
        user1.setId(userDAO.findUserByUsername(user1.getUsername()).getId());
        user2.setId(userDAO.findUserByUsername(user2.getUsername()).getId());
    }

    private boolean checkForExistence(Message message) {
        return jdbcTemplate.queryForObject("select count(*) from t_message " +
                        "where sender = ? and receiver = ? and text = ? and time = ? and date = ?",
                Integer.class,
                message.getSender().getId(),
                message.getReceiver().getId(),
                message.getText(),
                message.getTime(),
                message.getDate()
        ) == 0;
    }

}
