package com.example.project.controller;

import com.example.project.entity.Module;
import com.example.project.entity.Post;
import com.example.project.result.Result;
import com.example.project.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ModuleController {
    @Autowired
    private IModuleService iModuleService;

    @CrossOrigin
    @RequestMapping(value = "/api/getmodule",method = RequestMethod.GET)
    @ResponseBody
    public Result getmodule(@RequestParam("choice")int choice){
        Result result;
        if(choice ==1){
            //get post list of life module
            List<Post> posts = iModuleService.getPosts("life");
            result = Result.ok(posts);
        }
        else if(choice == 2){
            //get module list of knowledge module
            List<Module> modules = iModuleService.getModules();
            result = Result.ok(modules);
        }
        else{
            result = new Result(400,"Unknown Command",null);
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/searchmodule",method = RequestMethod.GET)
    @ResponseBody
    public Result searchmodule(@RequestParam("key")String key){
        List<Module> modules = iModuleService.searchModule(key);
        return Result.ok(modules);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/getposts",method = RequestMethod.GET)
    @ResponseBody
    public Result getposts(@RequestParam("module_name")String module_name){
        List<Post> posts = iModuleService.getPosts(module_name);
        return Result.ok(posts);
    }

}
