package todomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private Map<Integer, Status> mapStatuses;
    private List<Status> listStatus;

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
    public String todoList(@RequestParam(value = "status[]", required = false) int[] statuses, Model model) {
        checkStatuses(statuses);
        model.addAttribute("listStatus", listStatus);
        model.addAttribute("toDoTask", new ToDoTask());
        model.addAttribute("listToDo", this.toDoService.listToDo(statuses));
        return "todolist";
    }

    @RequestMapping(value = "todolist/add", method = RequestMethod.POST)
    public String addToDoTask(@ModelAttribute("toDoTask") ToDoTask toDoTask) {
        if (toDoTask.getId() == 0) this.toDoService.addToDo(toDoTask);
        else this.toDoService.updateToDo(toDoTask);

        return "redirect:/todolist";
    }

    @RequestMapping(value = "remove/{id}")
    public String removeToDoTask(@PathVariable("id") int id) {
        this.toDoService.removeToDo(id);
        return "redirect:/todolist";
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


    private void checkStatuses(int[] sts) {
       getStatuses();
        if (sts != null) {
            for (Status status : mapStatuses.values()) {
                status.setSelected(false);
            }
            for (int id : sts) {
                mapStatuses.get(id).setSelected(true);
            }
        }
    }

    private void getStatuses() {
        if (listStatus == null) {
            loadStatuses();
        }
    }

    private void loadStatuses() {
        listStatus = this.toDoService.listStatus();
        mapStatuses = mapStatuses(listStatus);
    }
}
