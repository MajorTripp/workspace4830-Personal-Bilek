package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE taskBilek (
  id INT NOT NULL AUTO_INCREMENT,    
  deadline VARCHAR(150) NOT NULL,   
  description VARCHAR(150) NOT NULL, 
  assignee VARCHAR(150) NOT NULL,
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "taskBilek")
public class TaskBilek {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column deadline. Without it, it will use method deadline
   private Integer id;

   @Column(name = "deadline")
   private String deadline;

   @Column(name = "description")
   private String description;
   
   @Column(name = "assignee")
   private String assignee;

   public TaskBilek() {
   }

   public TaskBilek(Integer id, String deadline, String description, String assignee) {
      this.id = id;
      this.deadline = deadline;
      this.description = description;
      this.assignee = assignee;
   }

   public TaskBilek(String deadline, String description, String assignee) {
      this.deadline = deadline;
      this.description = description;
      this.assignee = assignee;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getDeadline() {
      return deadline;
   }

   public void setDeadline(String deadline) {
      this.deadline = deadline;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
   
   public String getAssignee() {
	   return assignee;
   }
   
   public void setAssignee(String assignee) {
	   this.assignee = assignee;
   }

   @Override
   public String toString() {
      return "TaskBilek: " + this.id + ", " + this.deadline + ", " + this.description + ", " + this.assignee;
   }
}