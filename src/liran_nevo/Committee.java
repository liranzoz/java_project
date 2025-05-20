package liran_nevo;

import java.util.Arrays;

import static liran_nevo.eStatus.*;

public class Committee {
    private String name;
    private Lecturer[] lecturers = new Lecturer[0];
    private int numOfLecturers = 0;
    private Lecturer head;

    public Committee(String name, Lecturer head) {
        setName(name);
        setHead(head);
    }

    public void removeLecturerByName(String name) throws LecturerException {
        if (!Util.isExist(name,lecturers,numOfLecturers)){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        if (Util.getLecturerFromName(name,lecturers).equals(this.head)){
            throw new LecturerException(LECT_IS_HEAD.toString());
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i].getName().equals(name)) {
                for (int j = i; j < numOfLecturers - 1; j++) {
                    lecturers[j] = lecturers[j + 1];
                }

            }
        }
        lecturers[numOfLecturers - 1] = null;
        numOfLecturers--;
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

    public void addLecturerToCommittee(Lecturer lecturer) throws LecturerException {
        if (this.head.equals(lecturer)){
            throw new LecturerException(LECT_IS_HEAD.toString());
        }
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