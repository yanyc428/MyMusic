package utils;

import java.util.List;

public class ListGenerator<t> {

    private List<t> list;
    private int length;
    private int pointer=0;
    private int size;

    public ListGenerator(List<t> list, int length) {
        this.list = list;
        this.length = length;
        this.size = list.size();
    }

    public List<t> get(){
        if (this.size > this.pointer + this.length){
            this.pointer += this.length;
            return list.subList(this.pointer - this.length, this.pointer);
        }else{
            List<t> l = list.subList(this.pointer, this.size);
            this.pointer = this.size;
            return l;
        }
    }

    public boolean next(){
        if (this.size > this.pointer){
            return true;
        }
        return false;
    }

}
