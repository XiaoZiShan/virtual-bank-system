package com.ii.app.controllers;

import com.ii.app.dto.in.ConversationIn;
import com.ii.app.dto.out.ConversationOut;
import com.ii.app.models.enums.ConversationDirection;
import com.ii.app.services.interfaces.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<ConversationOut> findAll() {
        return conversationService.findAll();
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public List<ConversationOut> findAllEmployeeAdminDirection() {
        return conversationService.findByConversationDirection(ConversationDirection.ConversationDirectionType.EMPLOYEE_TO_ADMIN);
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_EMPLOYEE"})
    public List<ConversationOut> findAllUserToEmployeeDirection() {
        return conversationService.findByConversationDirection(ConversationDirection.ConversationDirectionType.USER_TO_EMPLOYEE);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ConversationOut create(@RequestBody ConversationIn conversationIn) {
        return conversationService.create(conversationIn);
    }


}
