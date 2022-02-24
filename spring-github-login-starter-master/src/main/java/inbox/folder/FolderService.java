package inbox.folder;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class FolderService{

    @Autowired
    private FolderRepository repo;


    public List<Folder> fetchDefaultFolders(String userName){
        return Arrays.asList(
            new Folder(userName, "Inbox", "blue"),
            new Folder(userName, "Sent Items", "green"),
            new Folder(userName, "Important", "red")
        );
    }

    @RequestMapping("/createFolders")
	public  void  createFolder(){
		var myFolder = new Folder();
		myFolder.setColor("green");
        myFolder.setLabel("label");

        myFolder.setUserId("Agam Sidhu");
		 repo.save(myFolder);

         var myFolder2 = new Folder();
         myFolder2.setColor("black");
        myFolder2.setLabel("label2");

        myFolder2.setUserId("Agam Sidhu");
		 repo.save(myFolder2);
		
	}

}