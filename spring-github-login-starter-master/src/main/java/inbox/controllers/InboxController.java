package inbox.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import inbox.emaillist.EmailListItem;
import inbox.emaillist.EmailListItemRepository;
import inbox.folder.FolderRepository;
import inbox.folder.FolderService;

@Controller
public class InboxController {

    @Autowired
    private FolderRepository repo;
    @Autowired
    private FolderService folderService;
    @Autowired
    private EmailListItemRepository emailRepo;

    @GetMapping(value = "/")
    public String homePage(@AuthenticationPrincipal OAuth2User principal, Model model) {

        //Check if user is logged in
        if (principal == null || !StringUtils.hasText(principal.getAttribute("name"))) 
        {
            return "index";
        }

        //fetch folders
        String userName = principal.getAttribute("name");
        model.addAttribute("userName", userName);
        System.out.println("username = " + userName);
        var allFolders = repo.findAllByuserId(userName);
        model.addAttribute("userFolders", allFolders);
        var defaultFolders = folderService.fetchDefaultFolders(userName);
        model.addAttribute("defaultFolders", defaultFolders);


        //fetch emails
        String folderlabel = "Inbox";
        List<EmailListItem> emailList = emailRepo.findAllByKeyIdAndKey_Label(userName, folderlabel);
        model.addAttribute("emailList", emailList);


        return "inbox-page";
    }
}
