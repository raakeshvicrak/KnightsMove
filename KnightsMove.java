/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightsmove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 *
 * @author raakeshpremkumar
 */
public class KnightsMove {
    
    private static ArrayList<LinkedHashMap<Integer,Integer>> chess_board = new ArrayList<LinkedHashMap<Integer,Integer>>();  
    private static int source = 0;
    private static int destination = 0;
    private static ArrayList<ArrayList<String>> queue = new ArrayList<ArrayList<String>>();
    private static int queuecount = 0;
    private static byte stepcount = 1;
    private static HashMap<String, Integer> board_cell_value_pair = new HashMap<String,Integer>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        initialiseBoard();   
        
        printBoard();
        
        source = 0;
        destination = 1;
        
        byte source_row_count = (byte) (Math.floor(source/8.0));
        byte source_column_Count = (byte) (source % 8);
        
        byte destination_row_count = (byte) Math.floor(destination/8.0);
        byte destination_column_Count = (byte) (destination % 8);
        
        System.err.println(source_row_count+ " " +source_column_Count);
        ArrayList<String> depth_nodes = new ArrayList<String>();
        
        depth_nodes.add(source_row_count+","+source_column_Count);
        queue.add(depth_nodes);
        depth_nodes = new ArrayList<String>();
        
        startWeighing();
        //System.err.println(""+board_cell_value_pair);
        
        if(board_cell_value_pair.containsKey(destination_row_count+","+destination_column_Count)){
            System.err.println(""+destination_row_count+","+destination_column_Count);
            System.err.println(""+board_cell_value_pair.get(destination_row_count+","+destination_column_Count));
        }
        
    }
    
    private static void startWeighing(){
        while(true){
            if(queuecount >= queue.size()){
                break;
            }
            ArrayList<String> queue_item = queue.get(queuecount);
            //System.err.println(queue_item.size()+" "+queue_item);
            ArrayList<String> temp_list = new ArrayList<String>();
            queuecount++;
            for (int i = 0; i < queue_item.size(); i++) {
                String position = queue_item.get(i);
                String position_split[] = position.split(",");
                byte position_split_row = Byte.valueOf(position_split[0]);
                byte position_split_column = Byte.valueOf(position_split[1]);
                
                if(((position_split_row + 1) <= 7) && (position_split_column+2)<=7){
                    //System.err.println((position_split_row + 1)+" "+(position_split_column+3));
                    if(checkIfDuplicate((position_split_row + 1), (position_split_column+2), temp_list)){
                        temp_list.add((position_split_row + 1)+","+(position_split_column+2));
                        board_cell_value_pair.put((position_split_row + 1) +"," + (position_split_column+2), (int)stepcount);
                    }
                }
                if(((position_split_row + 1) <= 7) && (position_split_column-2)>=0){
                    //System.err.println((position_split_row + 1)+" "+(position_split_column+3));
                    if(checkIfDuplicate((position_split_row + 1), (position_split_column-2), temp_list)){
                        temp_list.add((position_split_row + 1)+","+(position_split_column-2));
                        board_cell_value_pair.put((position_split_row + 1) +"," + (position_split_column-2), (int)stepcount);
                    }
                }
                if(((position_split_row - 1) >= 0) && (position_split_column+2)<=7){
                    //System.err.println((position_split_row - 1)+" "+(position_split_column+3));
                    if(checkIfDuplicate((position_split_row - 1), (position_split_column+2), temp_list)){
                        temp_list.add((position_split_row - 1)+","+(position_split_column+2));
                        board_cell_value_pair.put((position_split_row + 1) +","+ (position_split_column+2), (int)stepcount);
                    } 
                }
                if(((position_split_row - 1) >= 0) && (position_split_column-2)>=0){
                    //System.err.println((position_split_row - 1)+" "+(position_split_column+3));
                    if(checkIfDuplicate((position_split_row - 1), (position_split_column-2), temp_list)){
                        temp_list.add((position_split_row - 1)+","+(position_split_column-2));
                        board_cell_value_pair.put((position_split_row + 1) +","+ (position_split_column-2), (int)stepcount);
                    }
                }
                if(((position_split_row + 2) <= 7) && (position_split_column+1)<=7){
                    //System.err.println((position_split_row + 3)+" "+(position_split_column+1));
                    if(checkIfDuplicate((position_split_row + 2), (position_split_column+1), temp_list)){
                        temp_list.add((position_split_row + 2)+","+(position_split_column+1));
                        board_cell_value_pair.put((position_split_row + 2) +","+ (position_split_column+1), (int)stepcount);
                    }
                }
                if(((position_split_row - 2) >= 0) && (position_split_column+1)<=7){
                    //System.err.println((position_split_row + 3)+" "+(position_split_column+1));
                    if(checkIfDuplicate((position_split_row - 2), (position_split_column+1), temp_list)){
                        temp_list.add((position_split_row - 2)+","+(position_split_column+1));
                        board_cell_value_pair.put((position_split_row - 2) +","+ (position_split_column+1), (int)stepcount);
                    }
                }
                if(((position_split_row + 2) <= 7) && (position_split_column-1)>=0){
                    //System.err.println((position_split_row + 3)+" "+(position_split_column-1));
                    //System.err.println(""+(position_split_row + 3)*8 + position_split_column);
                    if(checkIfDuplicate((position_split_row + 2), (position_split_column-1), temp_list)){
                        temp_list.add((position_split_row + 2)+","+(position_split_column-1));
                        board_cell_value_pair.put((position_split_row + 2) +","+ (position_split_column-1), (int)stepcount);
                    }
                }
                if(((position_split_row - 2) >= 0) && (position_split_column-1)>=0){
                    //System.err.println((position_split_row + 3)+" "+(position_split_column-1));
                    if(checkIfDuplicate((position_split_row - 2), (position_split_column-1), temp_list)){
                        temp_list.add((position_split_row - 2)+","+(position_split_column-1));
                        board_cell_value_pair.put((position_split_row - 2) +","+ (position_split_column-1), (int)stepcount);
                    }
                }
            }
            if(!temp_list.isEmpty()){
                queue.add(temp_list);
            }
            stepcount++;
            //break;
        }
    }
    
    private static boolean checkIfDuplicate(int row, int column,ArrayList<String> temp){
        boolean present = false;
        for (ArrayList<String> arrayList : queue) {
            if(arrayList.contains(row+","+column)){
                present = true;
            }
        }
        if(temp.contains(row+","+column)){
            present = true;
        }
        
        return !present;
    }
    
    private static void printBoard(){
        for (Iterator<LinkedHashMap<Integer, Integer>> iterator = chess_board.iterator(); iterator.hasNext();) {
            LinkedHashMap<Integer, Integer> next = (LinkedHashMap<Integer, Integer>) iterator.next();
            System.out.println(next);
        }
    }
    
    private static void initialiseBoard(){
        byte current_loop_count = 1;
        LinkedHashMap<Integer, Integer> board_line_map = new LinkedHashMap<Integer, Integer>();
        for(int i = 0; i < 64; i++) {
            int board_line = (int) Math.ceil(i/7.0);
            if (i == 8){
                chess_board.add(board_line_map);
                board_line_map = new LinkedHashMap<Integer, Integer>();
                current_loop_count = (byte)board_line;
            }
            board_line_map.put(i, 0);
            if(i > 8 && i%8 == 7){
                chess_board.add(board_line_map);
                board_line_map = new LinkedHashMap<Integer, Integer>();
                current_loop_count = (byte)board_line;
            }
            
        }
        //chess_board.add(board_line_map);
    }  
}