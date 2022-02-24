package inbox.folder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FolderController{

    @Autowired
    private FolderRepository repo;

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