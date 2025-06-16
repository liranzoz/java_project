package liran_nevo;

import java.util.ArrayList;
import java.util.Arrays;

import static liran_nevo.eStatus.*;

public class Committee implements Cloneable, Collegable {
    private String name;
    private ArrayList<Lecturer> lecturers;
    private Lecturer head;

    public Committee(String name, Lecturer head) {
        setName(name);
        setHead(head);
    }

    @Override
    protected Committee clone() throws CloneNotSupportedException {
        Committee newCom = (Committee) super.clone();
        newCom.setName(name + " - new");
        newCom.setHead(this.head.clone());
//        newCom.setLecturers(Arrays.copyOf(lecturers, lecturers.size()));
        newCom.setLecturers((ArrayList<Lecturer>) lecturers.clone());
        for (int i = 0; i < lecturers.size(); i++) {
            newCom.getLecturers().set(i, lecturers.get(i).clone());
        }
        return newCom;
    }

    public void removeLecturerByName(String name) throws CollegeExceptions {
        if (!Util.isExist(name, lecturers)) { // lecturer dont exist - throw exeption
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        if (Util.getLecturerFromName(name, lecturers).equals(this.head)) { // lecturer is the head of com - cant remove, throw exeption
            throw new LecturerException(LECT_IS_HEAD.toString());
        }
//        for (int i = 0; i < lecturers.size(); i++) {
//            if (lecturers.get(i).getName().equals(name)) {
//                for (int j = i; j < lecturers.size() - 1; j++) {
//                    lecturers.set(i,lecturers.get(j + 1));
//                }
//
//            }
//        }
        if(!lecturers.remove(Util.getLecturerFromName(name,lecturers))){
            throw new CollegeExceptions(GENERAL_ERROR.toString());
        }

    }

    public int getNumOfArticles() {
        int numA = ((Doctor) head).getArticles().size();
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i) instanceof Doctor) {
                numA += ((Doctor) lecturers.get(i)).getArticles().size();
            }
        }
        return numA;
    }

        public int getNumOfLecturers () {
            return lecturers.size();
        }


        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public ArrayList<Lecturer> getLecturers () {
            return lecturers;
        }

        public void setLecturers (ArrayList<Lecturer> lecturers){
            this.lecturers = lecturers;
        }

        public Lecturer getHead () {
            return head;
        }

        public void setHead (Lecturer head){
            this.head = head;
            try {
                head.addCommittee(this);
            } catch (CollegeExceptions e) {
                System.out.println(e.getMessage());
            }
        }

        public void addLecturerToCommittee (Lecturer lecturer) throws LecturerException {
            if (this.head.equals(lecturer)) {
                throw new LecturerException(LECT_IS_HEAD.toString());
            }
            lecturers.add(lecturer);
        }

        @Override
        public String toString () {
            System.out.println();
            StringBuilder sb = new StringBuilder("committee: " + name + " | head of committee: " + head.getName() + "\nlecturers: ");
            if (lecturers.size() == 0) {
                sb.append("no lecturers\n");
                return sb.toString();
            }
            for (int i = 0; i < lecturers.size(); i++) {
                if (i + 1 != lecturers.size()) {
                    sb.append(lecturers.get(i).getName()).append(", ");
                } else {
                    sb.append(lecturers.get(i).getName());
                }
            }
            sb.append("\n");
            return sb.toString();
        }
    }