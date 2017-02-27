package todomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import todomanager.model.Status;
import todomanager.model.ToDoTask;
import todomanager.services.ToDoService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by butkoav on 23.02.2017.
 */
@Controller
@RequestMapping("/")
public class MainController {
    private ToDoService toDoService;

    @Autowired(required = true)
    @Qualifier(value = "toDoService")
    public void setToDoService(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        //  loadStatuses();
        //    model.addAttribute("listStatus", listStatus);
        //   model.addAttribute("toDoTask", new ToDoTask());
        //  model.addAttribute("listToDo", this.toDoService.listToDo());
        return "redirect:/todolist";
    }

    @RequestMapping(value = "todolist", method = RequestMethod.GET)
    public String todoList(@RequestParam MultiValueMap<String, String> allParams,
                           Model model) {

        model.addAttribute("listStatus", getStatuses(getSelectedStatuses(allParams)));
        model.addAttribute("toDoTask", new ToDoTask());
        model.addAttribute("listToDo", this.toDoService.listToDo(getSelectedStatuses(allParams)));
        return "todolist";
    }


    @RequestMapping(value = "datatable")
    public String getDataTable(@RequestParam MultiValueMap<String, String> allParams,
                               Model model)
    {
        model.addAttribute("listStatus", getStatuses(getSelectedStatuses(allParams)));
        model.addAttribute("toDoTask", new ToDoTask());
        model.addAttribute("listToDo", this.toDoService.listToDo(getSelectedStatuses(allParams)));
        return "datatable";
    }

    @RequestMapping(value = "todolist/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addToDoTask(@ModelAttribute("toDoTask") ToDoTask toDoTask) {
        if (toDoTask.getId() == 0) this.toDoService.addToDo(toDoTask);
        else this.toDoService.updateToDo(toDoTask);

        //    return "redirect:/todolist?status[]=1&";
    }

    /*  @RequestMapping(value = "todolist/add", method = RequestMethod.POST)
      public String addToDoTask(RedirectAttributes redirectAttributes,@RequestParam MultiValueMap<String, String> allParams) {
        //  if (toDoTask.getId() == 0) this.toDoService.addToDo(toDoTask);
       //   else this.toDoService.updateToDo(toDoTask);

         // redirectAttributes.addAllAttributes(allParams.get("status[]"));
           return "redirect:/todolist";
      }*/
    @RequestMapping(value = "remove/{id}")
    public String removeToDoTask(@PathVariable("id") int id) {
        this.toDoService.removeToDo(id);
        return "redirect:/todolist";
    }
    @RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeTask(@PathVariable("id") int id) {
        this.toDoService.removeToDo(id);
       // return "redirect:/todolist";
    }

 /*   @RequestMapping("edit/{id}")
    public String editToDoTask(@PathVariable("id") int id, Model model) {
        model.addAttribute("toDoTask", this.toDoService.getToDoById(id));
        model.addAttribute("listToDo", this.toDoService.listToDo());
        return "todolist";
    }*/

    private Map<Integer, Status> mapStatuses(List<Status> statuses) {
        if (statuses == null) return null;
        Map<Integer, Status> map = new TreeMap<Integer, Status>();

        for (Status status :
                statuses) {
            map.put(status.getId(), status);
        }

        return map;
    }


    private List<Status> getStatuses(int[] sts) {
        List<Status> listStatus = this.toDoService.listStatus();
        Map<Integer, Status> mapStatuses = mapStatuses(listStatus);
        if (sts != null) {
            for (Status status : mapStatuses.values()) {
                status.setSelected(false);
            }
            for (int id : sts) {
                mapStatuses.get(id).setSelected(true);
            }
        }
        return listStatus;
    }

    private int[] getSelectedStatuses(MultiValueMap<String, String> params) {
        int[] arr = null;
        List<String> strs;
        if (params.containsKey("status[]")) {
            strs = params.get("status[]");


            arr = new int[strs.size()];
            for (int i = 0; i < strs.size(); i++) {
                arr[i] = Integer.parseInt(strs.get(i));
            }
        }
        return arr;
    }
}
