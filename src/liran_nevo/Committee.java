package liran_nevo;

import java.util.Arrays;

public class Committee {
    private String name;
    private Lecturer[] lecturers = new Lecturer[0];
    private int numOfLecturers = 0;
    private Lecturer head;

    public Committee(String name, Lecturer head) {
        setName(name);
        setHead(head);
    }

    public boolean removeLecturerByName(String name) {
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i].getName().equals(name)) {
                for (int j = i; j < numOfLecturers - 1; j++) {
                    lecturers[j] = lecturers[j + 1];
                }
                lecturers[numOfLecturers - 1] = null;
                return true;
            }
        }
        return false;
    }

    public int getNumOfLecturers() {
        return numOfLecturers;
    }

    public void setNumOfLecturers(int numOfLecturers) {
        this.numOfLecturers = numOfLecturers;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public Lecturer getHead() {
        return head;
    }

    public void setHead(Lecturer head) {
            this.head = head;
            try {
                head.addCommittee(this);
            }catch (CollegeExceptions e){
                System.out.println(e.getMessage());
            }
    }

    public void addLecturerToCommittee(Lecturer lecturer) {
        if(numOfLecturers==lecturers.length){
            lecturers= Arrays.copyOf(lecturers,lecturers.length==0?1:lecturers.length*2);
        }
        lecturers[numOfLecturers++]=lecturer;
    }

    @Override
    public String toString() {
        System.out.println();
        StringBuilder sb = new StringBuilder("committee: " + name + " | head of committee: " + head.getName() + "\nlecturers: ");
        if (numOfLecturers == 0) {
            sb.append("no lecturers\n");
            return sb.toString();
        }
            for (int i = 0; i < numOfLecturers; i++) {
                if (i + 1 != numOfLecturers) {
                    sb.append(lecturers[i].getName()).append(", ");
                } else {
                    sb.append(lecturers[i].getName());
                }
            }
        sb.append("\n");
        return sb.toString();
    }
}