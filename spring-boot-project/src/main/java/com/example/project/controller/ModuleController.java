package com.example.project.controller;

import com.example.project.entity.HotModule;
import com.example.project.entity.Module;
import com.example.project.entity.Post;
import com.example.project.entity.User;
import com.example.project.mapper.ModuleMapper;
import com.example.project.mapper.PostMapper;
import com.example.project.mapper.UserMapper;
import com.example.project.result.Result;
import com.example.project.service.IModuleService;
import com.example.project.utils.HTMLUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
public class ModuleController {
    @Autowired
    private IModuleService iModuleService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private PostMapper postMapper;
    @Resource
    private ModuleMapper moduleMapper;

    @CrossOrigin
    @ApiOperation("Module Creation Request")
    @RequestMapping(value="/api/createmodulerequest", method = RequestMethod.POST)
    @ResponseBody
    public Result createmodulerequest(@RequestParam("module_name")String module_name){
        int flag = iModuleService.createModule(module_name);
        if(flag == 1)
            return Result.fail("Module with the same name has existed");
        else //if(flag == 0)
            return Result.ok("Successfully request, please wait for admin to review");
    }

    @CrossOrigin
    @ApiOperation("Get Module Creation Request List")
    @RequestMapping(value = "/api/getrequests",method = RequestMethod.GET)
    @ResponseBody
    public Result getmodule(){
        List<HashMap<String,Object>> list = moduleMapper.getRequests();
        return Result.ok(list);
    }

    @CrossOrigin
    @ApiOperation("Review Module Creation")
    @RequestMapping(value="/api/createmodule", method = RequestMethod.POST)
    @ResponseBody
    public Result createmodule(@RequestParam("module_name")String module_name){
        moduleMapper.insertModule(module_name);
        moduleMapper.deleteRequest(module_name);
        return Result.ok("A new knowledge module is created");
    }

    @CrossOrigin
    @ApiOperation("After click the dropdown menu of 'mudule' at the navbar" +
            "choice=1-->life choice=2-->knowledge")
    @RequestMapping(value = "/api/getmodule",method = RequestMethod.GET)
    @ResponseBody
    public Result getmodule(@RequestParam("choice")int choice){
        Result result;
        if(choice ==1){
            //get post list of life module
            List<Post> posts = iModuleService.getPosts("life");
            result = Result.ok(HTMLUtils.tohtmls(posts));
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
    @ApiOperation("get the admin list of a module")
    @RequestMapping(value = "/api/getadmins",method = RequestMethod.GET)
    @ResponseBody
    public Result getAdmins(@RequestParam("module_name")String module_name){
        List<String> admins = moduleMapper.getAdmins(module_name);
        return Result.ok(admins);
    }


    @CrossOrigin
    @ApiOperation("get user's role at a module")
    @RequestMapping(value = "/api/getrole",method = RequestMethod.GET)
    @ResponseBody
    public Result getRole(@RequestParam("username")String username,
                          @RequestParam("module_name")String module_name){
        User user = userMapper.selectByPrimaryKey(username);
        if(Objects.equals(user.getModule_admin(),module_name)){
            return Result.ok("admin");
        }
        else {
            return Result.ok("user");
        }
    }

    @CrossOrigin
    @ApiOperation("Search Knowledge Module via search key")
    @RequestMapping(value = "/api/searchmodule",method = RequestMethod.GET)
    @ResponseBody
    public Result searchmodule(@RequestParam("key")String key){
        List<Module> modules = iModuleService.searchModule(key);
        return Result.ok(modules);
    }

    @CrossOrigin
    @ApiOperation("Get posts of a user at a module")
    @RequestMapping(value = "/api/getselfposts",method = RequestMethod.GET)
    @ResponseBody
    public Result getselfposts(@RequestParam("module_name")String module_name,
                               @RequestParam("username")String username){
        List<Post> posts = postMapper.getPosts(username,module_name);
        return Result.ok(HTMLUtils.tohtmls(posts));
    }

    @CrossOrigin
    @ApiOperation("Get Posts of a mudule by module_name")
    @RequestMapping(value = "/api/getposts",method = RequestMethod.GET)
    @ResponseBody
    public Result getposts(@RequestParam("module_name")String module_name){
        List<Post> posts = iModuleService.getPosts(module_name);
        return Result.ok(HTMLUtils.tohtmls(posts));
    }

    @CrossOrigin
    @ApiOperation("Pin a post to the top of a module")
    @RequestMapping(value = "/api/totop",method = RequestMethod.POST)
    @ResponseBody
    public Result totop(@RequestParam("post_id")int post_id){
        //refresh the page
        //return Result.ok(HTMLUtils.tohtmls(posts));
        int flag=iModuleService.toTop(post_id);
        if(flag == 0)
            return Result.ok("Pin post to top successfully");
        else if(flag == 1)
            return Result.fail("There has been sufficient posts at the top");
        else//flag == 2
            return Result.ok("Cancel top");
    }

    @CrossOrigin
    @ApiOperation("Get user's points at a module")
    @RequestMapping(value="/api/getpoints", method = RequestMethod.POST)
    @ResponseBody
    //request params: module_name of life module: "life", when frontend send http request
    public Result getpoints(@RequestParam("username")String username,
                            @RequestParam("module_name")String module_name){
        int point = iModuleService.getPoints(username,module_name);
        return Result.ok(point);
    }

    @CrossOrigin
    @ApiOperation("Apply for admin of a module")
    @RequestMapping(value = "/api/applyadmin", method = RequestMethod.POST)
    @ResponseBody
    public Result applym(@RequestParam("username")String username,
                         @RequestParam("module_name")String module_name){
        int res = iModuleService.applym(username,module_name);
        if(res == 0)
            return Result.ok("apply successfully");
        else if(res == 1)
            return Result.fail("you have been an admin of certain module");
        else if(res ==2)
            return Result.fail("the module has had enough admins");
        else//res =3
            return Result.fail("dont have enough points to become an admin");
    }

    @CrossOrigin
    @ApiOperation("Quit module admin")
    @RequestMapping(value = "/api/deladmin", method = RequestMethod.POST)
    @ResponseBody
    public Result deladmin(@RequestParam("username")String username){
        iModuleService.quitAdmin(username);
        return Result.ok("quit successfully");
    }

    @CrossOrigin
    @ApiOperation("Edit module introduction")
    @RequestMapping(value = "/api/editintro", method = RequestMethod.POST)
    @ResponseBody
    public Result editintro(@RequestParam("module_name")String module_name,
                            @RequestParam("new_intro")String new_intro){
        iModuleService.editintro(module_name,new_intro);
        return Result.ok("modify successfully");
    }

    @CrossOrigin
    @ApiOperation("Get Posts of a mudule by module_name")
    @RequestMapping(value = "/api/homeposts",method = RequestMethod.GET)
    @ResponseBody
    public Result homeposts(){
        List<HotModule> hotmodules = iModuleService.gethotmodules();
        return Result.ok(hotmodules);
    }


}
