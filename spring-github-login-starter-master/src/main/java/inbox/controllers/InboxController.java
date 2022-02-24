package inbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import inbox.folder.FolderRepository;
import inbox.folder.FolderService;

@Controller
public class InboxController {

    //detect if user is logged in

    @Autowired private FolderRepository repo;
    @Autowired private FolderService folderService;

    @GetMapping(value = "/")
    public String homePage(@AuthenticationPrincipal OAuth2User principal, Model model) {

        if(principal == null || !StringUtils.hasText(principal.getAttribute("name"))){
            return "index";

        }
        
            String userName = principal.getAttribute("name");
            System.out.println("username = " + userName);
            var allFolders = repo.findAllByuserId(userName);
            model.addAttribute("userFolders", allFolders);
            var defaultFolders = folderService.fetchDefaultFolders(userName);
            model.addAttribute("defaultFolders", defaultFolders);
            model.addAttribute("userName", userName);

            return "inbox-page";
        }
    }
    

