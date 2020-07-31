package com.stnikolay.ChatAPI.DAO.message;

import com.stnikolay.ChatAPI.DAO.user.UserDAO;
import com.stnikolay.ChatAPI.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserDAO userDAO;

    @Override
    public Message sendMessage(Message message) {

        message.getSender().setId(userDAO.findUserByUsername(message.getSender().getUsername()).getId());
        message.getReceiver().setId(userDAO.findUserByUsername(message.getReceiver().getUsername()).getId());

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
