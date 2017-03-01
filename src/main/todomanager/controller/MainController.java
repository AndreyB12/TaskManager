package todomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todomanager.model.Status;
import todomanager.model.ToDoTask;
import todomanager.model.View;
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
        return "redirect:todolist";
    }

    @RequestMapping(value = "todolist", method = RequestMethod.GET)
    public String todoList(@RequestParam(value = "status", required = false) String[] statuses,
                           Model model) {

        model.addAttribute("listStatus", getStatuses(getSelectedStatuses(statuses)));
        return "todolist";
    }


    @RequestMapping(value = "datatable")
    public String getDataTable(@RequestParam(value = "status", required = true) String[] statuses,
                               @RequestParam(value = "rowsOnPage", required = true) int rowsOnPage,
                               @RequestParam(value = "firstId", required = true) int firstId,
                               Model model) {
        View view = new View();
        view.setRowsOnPage(rowsOnPage);
        view.setStatuses(getSelectedStatuses(statuses));
        view.setCurrentPage(1);
        view.setFirstId(firstId);
        view.setRows(toDoService.countRows(view.getStatuses()));
        view.setRowsBefore(toDoService.countRowsBefore(view.getFirstId(), view.getStatuses()));
        view.calcPagesCount(true);
        List<ToDoTask> toDoTasks = this.toDoService.listToDoNext(view.getFirstId(), view.getRowsOnPage(), view.getStatuses());
        if (toDoTasks.size() > 0) {
            view.setFirstId(toDoTasks.get(0).getId());
            view.setLastId(toDoTasks.get(toDoTasks.size() - 1).getId());
        }
        model.addAttribute("view", view);
        model.addAttribute("listStatus", getStatuses(view.getStatuses()));
        model.addAttribute("listToDo", toDoTasks);
        return "datatable";
    }


    @RequestMapping(value = "datatable/next")
    public String getFirstPage(@RequestParam(value = "status", required = false) String[] statuses,
                               @RequestParam(value = "rowsOnPage", required = false) int rowsOnPage,
                               Model model) {

        int[] sts = getSelectedStatuses(statuses);
        View view = new View();
        model.addAttribute("view", view);
        model.addAttribute("listStatus", getStatuses(sts));
        model.addAttribute("listToDo", this.toDoService.listToDo(getSelectedStatuses(statuses)));
        return "datatable";
    }


    @RequestMapping(value = "todolist/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addToDoTask(@ModelAttribute("toDoTask") ToDoTask toDoTask) {
        if (toDoTask.getId() == 0) this.toDoService.addToDo(toDoTask);
        else this.toDoService.updateToDo(toDoTask);
    }

    /*  @RequestMapping(value = "todolist/add", method = RequestMethod.POST)
      public String addToDoTask(RedirectAttributes redirectAttributes,@RequestParam MultiValueMap<String, String> allParams) {
        //  if (toDoTask.getId() == 0) this.toDoService.addToDo(toDoTask);
       //   else this.toDoService.updateToDo(toDoTask);

         // redirectAttributes.addAllAttributes(allParams.get("status[]"));
           return "redirect:/todolist";
      }*/

    /*   @RequestMapping(value = "remove/{id}")
       public String removeToDoTask(@PathVariable("id") int id) {
           this.toDoService.removeToDo(id);
           return "redirect:/todolist";
       }*/
    @RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeTask(@PathVariable("id") int id) {
        this.toDoService.removeToDo(id);
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

    private int[] getSelectedStatuses(String[] statuses) {
        int[] arr = null;
        if (statuses != null) {
            arr = new int[statuses.length];
            for (int i = 0; i < statuses.length; i++) {
                arr[i] = Integer.parseInt(statuses[i]);
            }
        }
        return arr;
    }
}
