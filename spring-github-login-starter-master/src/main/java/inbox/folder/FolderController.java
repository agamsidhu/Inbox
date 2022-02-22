package inbox.folder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FolderController{

    @Autowired
    private FolderRepository repo;

    @RequestMapping("/getfolder")
	public  void  createFolder(){
		var myFolder = new Folder();
		myFolder.setColor("green");
        myFolder.setLabel("label");

        myFolder.setUserId("1");
		 repo.save(myFolder);
		
	}

}