package com.company;

import java.io.Console;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

enum flag {
    Harry,
    Filch,
    Cat,
    Cloak,
    Book,
    Exit,
    Empty,
    Occupied
}

class Pair<T,V> {
    private T first;
    private V second;

    public Pair(T firstElement, V secondElement) {
        first = firstElement;
        second = secondElement;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }
}

class Cell {
    private flag typeOfCell;
    private int heuristicVal;
    private int moveVal;
    private int costVal;
    private int orderInBackTrack;
    private int orderInAStar;
    private boolean calculated;
    private boolean visited;
    private boolean reachable;
    private Pair<Integer,Integer> coordinate;
    private Pair<Integer,Integer> prevCell;

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public int getOrderInAStar() {
        return orderInAStar;
    }

    public void setOrderInAStar(int orderInAStar) {
        this.orderInAStar = orderInAStar;
    }

    public int getOrderInBackTrack() {
        return orderInBackTrack;
    }

    public void setOrderInBackTrack(int orderInBackTrack) {
        this.orderInBackTrack = orderInBackTrack;
    }
    public Pair<Integer, Integer> getCoordinate() {
        return coordinate;
    }
    public boolean isCalculated() {
        return calculated;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Pair<Integer, Integer> getPrevCell() {
        return prevCell;
    }

    public void setPrevCell(int first, int second) {
        this.prevCell = new Pair<>(first,second);
    }

//    public void setCoordinate(int first, int second) {
//        this.coordinate = new Pair<>(first,second);
//    }

    public Cell(int first, int second) {
        setTypeOfCell(flag.Empty);
        calculated =  false;
        visited = false;
        reachable = false;
        this.coordinate = new Pair<>(first,second);
        costVal = 0;
        orderInBackTrack = 0;
        orderInAStar = 0;
    }

    public flag getTypeOfCell() {
        return typeOfCell;
    }

    public void setTypeOfCell(flag typeOfCell) {
        this.typeOfCell = typeOfCell;
        if (typeOfCell==flag.Filch) {
            moveVal = 2000;
        } else if (typeOfCell==flag.Cat) {
            moveVal = 2000;
        } else if (typeOfCell==flag.Occupied) {
            moveVal = 500;
        } else if (typeOfCell==flag.Harry) {
            moveVal = 0;
        } else {
            moveVal = 1;
        }
    }

    public int getHeuristicVal() {
        return heuristicVal;
    }

    public void setHeuristicVal(int heuristicVal) {
        this.heuristicVal = heuristicVal;
    }

    public int getMoveVal() {
        return moveVal;
    }

    public void setMoveVal(int moveVal) {
        this.moveVal = moveVal;
        costVal = moveVal + heuristicVal;
    }

    public void calculateCostVal(int prevVal) {
        calculated = true;
        costVal = moveVal + heuristicVal + prevVal;
    }

    public int getCostVal() {
        return costVal;
    }

    public int comparator(Cell cell) {
        return Integer.compare(this.getCostVal(), cell.getCostVal());
    }
}




class Map {
    private Cell[][] grid;
    public Pair<Integer,Integer> HarryCoordinate;
    public Pair<Integer,Integer> FilchCoordinate;
    public Pair<Integer,Integer> CatCoordinate;
    public Pair<Integer,Integer> BookCoordinate;
    public Pair<Integer,Integer> CloakCoordinate;
    public Pair<Integer,Integer> ExitCoordinate;
    private final SortedSet<Cell> cellSet;
    private static final int gridSize = 9;
    public Pair<Integer,Integer> reachableBookCoordinate;
    public Pair<Integer,Integer> reachableCloakCoordinate;
    public Pair<Integer,Integer> reachableExitCoordinate;
    private boolean cloakIsActivated;

    public Map() {
        grid = new Cell[gridSize][gridSize];
        for (int i=0; i<gridSize; i++) {
            for (int j=0 ; j<gridSize; j++) {
                grid[i][j] = new Cell(i,j);
            }
        }
        cellSet = new TreeSet<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                int costValCompare = Integer.compare(o1.getCostVal(), o2.getCostVal());
                if (costValCompare == 0)
                    return Integer.compare(o1.hashCode(), o2.hashCode());
                else
                    return costValCompare;
            }
        });
        reachableCloakCoordinate = new Pair<>(-1,-1);
        reachableExitCoordinate = new Pair<>(-1,-1);
        reachableBookCoordinate = new Pair<>(-1,-1);
        cloakIsActivated = false;
    }

    public void activateCloak(int first, int second) {
        if (first == CloakCoordinate.getFirst() && second== CloakCoordinate.getSecond())
            cloakIsActivated =true;
        for (int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                if (grid[i][j].getTypeOfCell()==flag.Occupied)
                    grid[i][j].setMoveVal(1);
            }
        }
    }

    public void checkAllReachable(int first, int second) {
        SortedSet<Cell> reachableCellSet = new TreeSet<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                int costValCompare = Integer.compare(o1.getCostVal(), o2.getCostVal());
                if (costValCompare == 0)
                    return Integer.compare(o1.hashCode(), o2.hashCode());
                else
                    return costValCompare;
            }
        });
        grid[first][second].setReachable(true);
        reachableCellSet.add(grid[first][second]);
        while (reachableCellSet.size()!=0) {
            int f = reachableCellSet.first().getCoordinate().getFirst();
            int s = reachableCellSet.first().getCoordinate().getSecond();
            reachableCellSet.remove(reachableCellSet.first());
            for(int i=f-1; i<f+2; i++) {
                for(int j=s-1; j<s+2; j++) {
                    if (i>-1 && i<gridSize && j>-1 && j<gridSize && !grid[i][j].isReachable() &&
                            ((cloakIsActivated)  || !(grid[i][j].getTypeOfCell()==flag.Occupied) )
                            && !(grid[i][j].getTypeOfCell()==flag.Filch) &&
                            !(grid[i][j].getTypeOfCell()==flag.Cat)) {
                        grid[i][j].setReachable(true);
                        reachableCellSet.add(grid[i][j]);
                        if (grid[i][j].getTypeOfCell()==flag.Book)
                            reachableBookCoordinate = new Pair<>(i,j);
                        if (grid[i][j].getTypeOfCell()==flag.Cloak)
                            reachableCloakCoordinate = new Pair<>(i,j);
                        if (grid[i][j].getTypeOfCell()==flag.Exit)
                            reachableExitCoordinate = new Pair<>(i,j);
                    }
                }
            }
        }
    }

    public void resetAllReachable() {
        for (int i = 0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                grid[i][j].setReachable(false);
            }
        }
        reachableExitCoordinate = new Pair<>(-1,-1);
        reachableBookCoordinate = new Pair<>(-1,-1);
        reachableCloakCoordinate = new Pair<>(-1,-1);
    }

    public boolean isBookReachable() {
        return (reachableBookCoordinate.getFirst()!=-1 && reachableBookCoordinate.getSecond()!=-1);
    }

    public boolean isExitReachable() {
        return (reachableExitCoordinate.getFirst()!=-1 && reachableExitCoordinate.getSecond()!=-1);
    }

    public boolean isCloakReachable() {
        return (reachableCloakCoordinate.getFirst()!=-1 && reachableCloakCoordinate.getSecond()!=-1);
    }

    private void addCellsInSet(int first, int second) {
//        System.out.println(first+" "+second);
//        System.out.println(cellSet.first().getCoordinate().getFirst()+" "+cellSet.first().getCoordinate().getSecond());
        cellSet.remove(cellSet.first());
        for(int i=first-1; i<first+2; i++) {
            for(int j=second-1; j<second+2; j++) {
                if (i>-1 && i<gridSize && j>-1 && j<gridSize && !grid[i][j].isCalculated()) {
                    grid[i][j].calculateCostVal(grid[first][second].getCostVal());
                    grid[i][j].setPrevCell(first,second);
                    cellSet.add(grid[i][j]);
                }
            }
        }
//        printMap();
    }

    public void findWayUsingAStar(int firstS, int secondS, int firstE, int secondE) {
        for (int i=0; i<gridSize; i++) {
            for (int j=0 ; j<gridSize; j++) {
                grid[i][j].setHeuristicVal(Math.max( Math.abs(i-firstE),  Math.abs(j-secondE)));
            }
        }
//        printMap();
        grid[firstS][secondS].calculateCostVal(0);
        grid[firstS][secondS].setPrevCell(-1,-1);
        cellSet.add(grid[firstS][secondS]);
        addCellsInSet(firstS,secondS);

        while (cellSet.first().getCoordinate().getFirst() != firstE || cellSet.first().getCoordinate().getSecond() != secondE ) {
            //cellSet.remove(cellSet.first());
            addCellsInSet(cellSet.first().getCoordinate().getFirst(), cellSet.first().getCoordinate().getSecond());
        }
        ArrayList<Pair<Integer,Integer>> way = new ArrayList<>();
        Pair<Integer,Integer> currentCell = new Pair<>(firstE,secondE);

        while (currentCell.getFirst()!=-1 && currentCell.getSecond()!=-1) {
            way.add(currentCell);
            currentCell = new Pair<>(grid[currentCell.getFirst()][currentCell.getSecond()].getPrevCell().getFirst(),grid[currentCell.getFirst()][currentCell.getSecond()].getPrevCell().getSecond());
        }
        for (int i=way.size()-1; i>= 0; i--) {
            grid[way.get(i).getFirst()][way.get(i).getSecond()].setOrderInAStar(way.size()-i);
//            System.out.println(way.get(i).getFirst()+" "+way.get(i).getSecond());
        }
    }

    private boolean cellIsSafe(int first, int second) {
        if (first>-1 && second>-1 && first<gridSize && second<gridSize) {
            if (cloakIsActivated) {
                return (grid[first][second].getTypeOfCell()!=flag.Filch
                        && grid[first][second].getTypeOfCell()!=flag.Cat && !grid[first][second].isVisited());
            } else {
                return (grid[first][second].getTypeOfCell() != flag.Occupied && grid[first][second].getTypeOfCell()!=flag.Filch
                        && grid[first][second].getTypeOfCell()!=flag.Cat && !grid[first][second].isVisited());
            }
        } else {
            return false;
        }
    }

    public boolean findWayUsingBacktracking(int firstS, int secondS, int firstE, int secondE, int orderInBacktrack) {
        orderInBacktrack++;
        if (firstS==firstE && secondS == secondE) {
            grid[firstS][secondS].setOrderInBackTrack(orderInBacktrack);
            //System.out.println(firstS+" "+secondS);
            return true;
        } else {
            int up = Math.max(firstE - firstS, 0);
            int right = Math.max(secondE - secondS, 0);
            int down = Math.max(firstS - firstE, 0);
            int left = Math.max(secondS - secondE, 0);
            grid[firstS][secondS].setVisited(true);

            ArrayList<Integer> verticalMove = new ArrayList<>();
            ArrayList<Integer> horizontalMove = new ArrayList<>();

            if (up!=0) {
                verticalMove.add(1);
                verticalMove.add(0);
                verticalMove.add(-1);
                if (left!=0) {
                    horizontalMove.add(-1);
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                } else if (right!=0) {
                    horizontalMove.add(1);
                    horizontalMove.add(0);
                    horizontalMove.add(-1);
                } else {
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                    horizontalMove.add(-1);
                }
            } else if (down!=0) {
                verticalMove.add(-1);
                verticalMove.add(0);
                verticalMove.add(1);
                if (left!=0) {
                    horizontalMove.add(-1);
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                } else if (right!=0) {
                    horizontalMove.add(1);
                    horizontalMove.add(0);
                    horizontalMove.add(-1);
                } else {
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                    horizontalMove.add(-1);
                }
            } else {
                verticalMove.add(0);
                verticalMove.add(1);
                verticalMove.add(-1);
                if (left!=0) {
                    horizontalMove.add(-1);
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                } else if (right!=0) {
                    horizontalMove.add(1);
                    horizontalMove.add(0);
                    horizontalMove.add(-1);
                } else {
                    horizontalMove.add(0);
                    horizontalMove.add(1);
                    horizontalMove.add(-1);
                }
            }

            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (verticalMove.get(i)!=0 || horizontalMove.get(j)!=0)
                        if (cellIsSafe(firstS+verticalMove.get(i),secondS+horizontalMove.get(j)) &&
                                findWayUsingBacktracking(firstS+verticalMove.get(i),secondS+horizontalMove.get(j), firstE, secondE, orderInBacktrack)) {
                            grid[firstS][secondS].setOrderInBackTrack(orderInBacktrack);
//                            System.out.println(firstS + " " + secondS);
                            return true;
                        }
                }
            }
            return false;
        }
    }

    public Cell getCell(int i, int j) {
        return grid[i][j];
    }

    public void setHarry() {
        HarryCoordinate = new Pair<>(0,0);
        grid[HarryCoordinate.getFirst()][HarryCoordinate.getSecond()].setTypeOfCell(flag.Harry);
    }

    public void setFilch(int first, int second) {
        FilchCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Filch);
        for (int i=first-2; i<=first+2; i++) {
            for (int j=second-2; j<=second+2; j++) {
                if (i<gridSize && i>-1 && j<gridSize && j>-1) {
                    if (grid[i][j].getTypeOfCell()==flag.Empty)
                       grid[i][j].setTypeOfCell(flag.Occupied);
                }
            }
        }
    }

    public void setCat(int first, int second) {
        CatCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Cat);
        for (int i=first-1; i<=first+1; i++) {
            for (int j=second-1; j<=second+1; j++) {
                if (i<gridSize && i>-1 && j<gridSize && j>-1) {
                    if (grid[i][j].getTypeOfCell()==flag.Empty)
                        grid[i][j].setTypeOfCell(flag.Occupied);
                }
            }
        }
    }

    private void setBook(int first, int second) {
        BookCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Book);
    }

    private void setCloak(int first, int second) {
        CloakCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Cloak);
    }

    private void setExit(int first, int second) {
        ExitCoordinate = new Pair<>(first,second);
        grid[first][second].setTypeOfCell(flag.Exit);
    }

    public void generateMap() {
        Random rnd = new Random();
        setHarry();

        boolean notSuitable = true;
        int first = 0;
        int second = 0;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            for (int i=first-2; i<=first+2; i++) {
                for (int j=second-2; j<=second+2; j++) {
                    if (i<gridSize && i>-1 && j<gridSize && j>-1) {
                        if (grid[i][j].getTypeOfCell()!=flag.Empty)
                            notSuitable = true;
                    }
                }
            }
        }
        setFilch(first, second);

        notSuitable = true;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            for (int i=first-1; i<=first+1; i++) {
                for (int j=second-1; j<=second+1; j++) {
                    if (i<gridSize && i>-1 && j<gridSize && j>-1) {
                        if (grid[i][j].getTypeOfCell()!=flag.Empty && grid[i][j].getTypeOfCell()!=flag.Occupied)
                            notSuitable = true;
                    }
                }
            }
        }
        setCat(first,second);

        notSuitable = true;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            if (grid[first][second].getTypeOfCell()!=flag.Empty) {
                notSuitable = true;
            }
        }
        setBook(first,second);

        notSuitable = true;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            if (grid[first][second].getTypeOfCell()!=flag.Empty) {
                notSuitable = true;
            }
        }
        setCloak(first,second);

        notSuitable = true;
        while (notSuitable) {
            notSuitable = false;
            first = rnd.nextInt(gridSize);
            second = rnd.nextInt(gridSize);
            if (grid[first][second].getTypeOfCell()!=flag.Empty) {
                notSuitable = true;
            }
        }
        setExit(first,second);
    }

    public void insertMap(int h1, int h2, int f1, int f2, int cat1, int cat2, int b1, int b2, int c1, int c2, int e1, int e2) {
        setHarry();
        setFilch(f1,f2);
        setCat(cat1,cat2);
        setBook(b1,b2);
        setCloak(c1,c2);
        setExit(e1,e2);
    }

    public void copyMap(Map someMap) {
        someMap.insertMap(HarryCoordinate.getFirst(),HarryCoordinate.getSecond(),
                FilchCoordinate.getFirst(), FilchCoordinate.getSecond(),
                CatCoordinate.getFirst(), CatCoordinate.getSecond(),
                BookCoordinate.getFirst(), BookCoordinate.getSecond(),
                CloakCoordinate.getFirst(), CloakCoordinate.getSecond(),
                ExitCoordinate.getFirst(), ExitCoordinate.getSecond());
        if (cloakIsActivated)
            someMap.activateCloak(CloakCoordinate.getFirst(), CloakCoordinate.getSecond());
    }

    public void printMap(int style) {
        System.out.println("-------------------------------------------------------------------------");
        if (style == 0) {
            for (int i=gridSize-1; i>-1; i--) {
                for (int k=0; k<5; k++) {
                    for (int j=0; j<gridSize; j++) {
                        System.out.print('|');
                        if (grid[i][j].getTypeOfCell()==flag.Harry && k==0) {
                            System.out.print("\u001B[33m"+"H  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Filch && k==0) {
                            System.out.print("\u001B[31m"+"F  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cat && k==0) {
                            System.out.print("\u001B[31m"+"C  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cloak && k==0) {
                            System.out.print("\u001B[33m"+"IC "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Book && k==0) {
                            System.out.print("\u001B[33m"+"B  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Exit && k==0) {
                            System.out.print("\u001B[33m"+"E  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Occupied && k==0) {
                            System.out.print("\u001B[31m"+"O  "+"\u001B[0m");
                        } else if (k==1) {
                            if (grid[i][j].getOrderInAStar()<10 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+ grid[i][j].getOrderInAStar() + "  "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInAStar()<100 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+grid[i][j].getOrderInAStar() + " "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInAStar()<1000 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+grid[i][j].getOrderInAStar()+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else if (k==2) {
                            if (grid[i][j].getCostVal()<10) {
                                System.out.print(grid[i][j].getCostVal()+"  ");
                            } else if (grid[i][j].getCostVal()<100) {
                                System.out.print(grid[i][j].getCostVal()+" ");
                            } else {
                                System.out.print(grid[i][j].getCostVal());
                            }
                        }  else if (k==3) {
                            if (grid[i][j].getOrderInBackTrack()<10 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack() + "  "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInBackTrack()<100 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack() + " "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInBackTrack()<1000 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack()+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else if (k==4) {
                            if (grid[i][j].isReachable()) {
                                System.out.print("\u001B[36m"+"R  "+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else {
                            System.out.print("   ");
                        }
                        for (int raw=3; raw<7; raw++) {
                            System.out.print(' ');
                        }
                    }
                    System.out.println('|');
                }
                System.out.println("-------------------------------------------------------------------------");
            }
        } else if (style==1) {
            for (int i=gridSize-1; i>-1; i--) {
                for (int k=0; k<3; k++) {
                    for (int j=0; j<gridSize; j++) {
                        System.out.print('|');
                        if (grid[i][j].getTypeOfCell()==flag.Harry && k==0) {
                            System.out.print("\u001B[33m"+"H  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Filch && k==0) {
                            System.out.print("\u001B[31m"+"F  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cat && k==0) {
                            System.out.print("\u001B[31m"+"C  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cloak && k==0) {
                            System.out.print("\u001B[33m"+"IC "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Book && k==0) {
                            System.out.print("\u001B[33m"+"B  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Exit && k==0) {
                            System.out.print("\u001B[33m"+"E  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Occupied && k==0) {
                            System.out.print("\u001B[31m"+"O  "+"\u001B[0m");
                        } else if (k==2) {
                            if (grid[i][j].getOrderInBackTrack()<10 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack() + "  "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInBackTrack()<100 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack() + " "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInBackTrack()<1000 && grid[i][j].getOrderInBackTrack()>0) {
                                System.out.print("\u001B[34m"+grid[i][j].getOrderInBackTrack()+"\u001B[0m");
                            } else {
                                System.out.print("   ");
                            }
                        } else {
                            System.out.print("   ");
                        }
                        for (int raw=3; raw<7; raw++) {
                            System.out.print(' ');
                        }
                    }
                    System.out.println('|');
                }
                System.out.println("-------------------------------------------------------------------------");
            }
        } else if (style == 2) {
            for (int i=gridSize-1; i>-1; i--) {
                for (int k=0; k<3; k++) {
                    for (int j=0; j<gridSize; j++) {
                        System.out.print('|');
                        if (grid[i][j].getTypeOfCell()==flag.Harry && k==0) {
                            System.out.print("\u001B[33m"+"H   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Filch && k==0) {
                            System.out.print("\u001B[31m"+"F   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cat && k==0) {
                            System.out.print("\u001B[31m"+"C   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Cloak && k==0) {
                            System.out.print("\u001B[33m"+"IC  "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Book && k==0) {
                            System.out.print("\u001B[33m"+"B   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Exit && k==0) {
                            System.out.print("\u001B[33m"+"E   "+"\u001B[0m");
                        } else if (grid[i][j].getTypeOfCell()==flag.Occupied && k==0) {
                            System.out.print("\u001B[31m"+"O   "+"\u001B[0m");
                        } else if (k==2) {
                            if (grid[i][j].getOrderInAStar()<10 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+ grid[i][j].getOrderInAStar() + "   "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInAStar()<100 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+grid[i][j].getOrderInAStar() + "  "+"\u001B[0m");
                            } else if (grid[i][j].getOrderInAStar()<1000 && grid[i][j].getOrderInAStar()>0) {
                                System.out.print("\u001B[32m"+grid[i][j].getOrderInAStar()+" "+"\u001B[0m");
                            } else {
                                System.out.print("    ");
                            }
                        } else if (k==1) {
                            if (grid[i][j].getCostVal()<10) {
                                System.out.print(grid[i][j].getCostVal()+"   ");
                            } else if (grid[i][j].getCostVal()<100) {
                                System.out.print(grid[i][j].getCostVal()+"  ");
                            } else if (grid[i][j].getCostVal()<1000) {
                                System.out.print(grid[i][j].getCostVal()+" ");
                            } else {
                                System.out.print(grid[i][j].getCostVal());
                            }
                        } else {
                            System.out.print("    ");
                        }
                        for (int raw=4; raw<7; raw++) {
                            System.out.print(' ');
                        }
                    }
                    System.out.println('|');
                }
                System.out.println("-------------------------------------------------------------------------");
            }
        }
    }
}

public class Main {

    public static void calculateWayBE(Map map1, Map map2, AtomicInteger numberOfStepsInBacktrack, AtomicInteger numberOfStepsInAStar) {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingAStar(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableBookCoordinate.getFirst(), map1.reachableBookCoordinate.getSecond());
        map1.findWayUsingBacktracking(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableBookCoordinate.getFirst(), map1.reachableBookCoordinate.getSecond(),0);
//        map1.printMap();

        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingAStar(map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),
                map2.reachableExitCoordinate.getFirst(), map2.reachableExitCoordinate.getSecond());
        map2.findWayUsingBacktracking(map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),
                map2.reachableExitCoordinate.getFirst(), map2.reachableExitCoordinate.getSecond(),0);
//        map2.printMap();

        int fp1 = map1.reachableBookCoordinate.getFirst();
        int sp1 = map1.reachableBookCoordinate.getSecond();
        int fp2 = map2.reachableExitCoordinate.getFirst();
        int sp2 = map2.reachableExitCoordinate.getSecond();
        numberOfStepsInBacktrack.set(map1.getCell(fp1,sp1).getOrderInBackTrack()-1 +
                map2.getCell(fp2,sp2).getOrderInBackTrack()-1);
        numberOfStepsInAStar.set(map1.getCell(fp1,sp1).getOrderInAStar()-1 +
                map2.getCell(fp2,sp2).getOrderInAStar()-1);
    }

    public static void calculateWayBCE(Map map1, Map map2, Map map3, AtomicInteger numberOfStepsInBacktrack, AtomicInteger numberOfStepsInAStar) {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingAStar(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableBookCoordinate.getFirst(), map1.reachableBookCoordinate.getSecond());
        map1.findWayUsingBacktracking(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableBookCoordinate.getFirst(), map1.reachableBookCoordinate.getSecond(),0);
//                    map1.printMap();
        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingAStar(map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),
                map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond());
        map2.findWayUsingBacktracking(map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),
                map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond(),0);

        map3.activateCloak(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond());
        map3.checkAllReachable(map3.HarryCoordinate.getFirst(), map3.HarryCoordinate.getSecond());
        map3.findWayUsingAStar(map3.reachableCloakCoordinate.getFirst(), map3.reachableCloakCoordinate.getSecond(),
                map3.reachableExitCoordinate.getFirst(), map3.reachableExitCoordinate.getSecond());
        map3.findWayUsingBacktracking(map3.reachableCloakCoordinate.getFirst(), map3.reachableCloakCoordinate.getSecond(),
                map3.reachableExitCoordinate.getFirst(), map3.reachableExitCoordinate.getSecond(),0);

        int fp1 = map1.reachableBookCoordinate.getFirst();
        int sp1 = map1.reachableBookCoordinate.getSecond();
        int fp2 = map2.reachableCloakCoordinate.getFirst();
        int sp2 = map2.reachableCloakCoordinate.getSecond();
        int fp3 = map3.reachableExitCoordinate.getFirst();
        int sp3 = map3.reachableExitCoordinate.getSecond();
        numberOfStepsInBacktrack.set(map1.getCell(fp1,sp1).getOrderInBackTrack()-1 +
                map2.getCell(fp2,sp2).getOrderInBackTrack()-1 +
                map3.getCell(fp3,sp3).getOrderInBackTrack()-1);
        numberOfStepsInAStar.set(map1.getCell(fp1,sp1).getOrderInAStar()-1 +
                map2.getCell(fp2,sp2).getOrderInAStar()-1 +
                map3.getCell(fp3,sp3).getOrderInAStar()-1);
    }

    public static void calculateWayCBE(Map map1, Map map2, Map map3, AtomicInteger numberOfStepsInBacktrack, AtomicInteger numberOfStepsInAStar) {
        map1.checkAllReachable(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond());
        map1.findWayUsingAStar(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableCloakCoordinate.getFirst(), map1.reachableCloakCoordinate.getSecond());
        map1.findWayUsingBacktracking(map1.HarryCoordinate.getFirst(), map1.HarryCoordinate.getSecond(),
                map1.reachableCloakCoordinate.getFirst(), map1.reachableCloakCoordinate.getSecond(),0);
//        map1.printMap();

        map2.activateCloak(map1.reachableCloakCoordinate.getFirst(), map1.reachableCloakCoordinate.getSecond());
        map2.checkAllReachable(map2.HarryCoordinate.getFirst(), map2.HarryCoordinate.getSecond());
        map2.findWayUsingAStar(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond(),
                map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond());
        map2.findWayUsingBacktracking(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond(),
                map2.reachableBookCoordinate.getFirst(), map2.reachableBookCoordinate.getSecond(),0);
//        map2.printMap();

        map3.activateCloak(map2.reachableCloakCoordinate.getFirst(), map2.reachableCloakCoordinate.getSecond());
        map3.checkAllReachable(map3.HarryCoordinate.getFirst(), map3.HarryCoordinate.getSecond());
        map3.findWayUsingAStar(map3.reachableBookCoordinate.getFirst(), map3.reachableBookCoordinate.getSecond(),
                map3.reachableExitCoordinate.getFirst(), map3.reachableExitCoordinate.getSecond());
        map3.findWayUsingBacktracking(map3.reachableBookCoordinate.getFirst(), map3.reachableBookCoordinate.getSecond(),
                map3.reachableExitCoordinate.getFirst(), map3.reachableExitCoordinate.getSecond(),0);
//        map3.printMap();

        int fp1 = map1.reachableCloakCoordinate.getFirst();
        int sp1 = map1.reachableCloakCoordinate.getSecond();
        int fp2 = map2.reachableBookCoordinate.getFirst();
        int sp2 = map2.reachableBookCoordinate.getSecond();
        int fp3 = map3.reachableExitCoordinate.getFirst();
        int sp3 = map3.reachableExitCoordinate.getSecond();
        numberOfStepsInBacktrack.set(map1.getCell(fp1,sp1).getOrderInBackTrack()-1 +
                map2.getCell(fp2,sp2).getOrderInBackTrack()-1 +
                map3.getCell(fp3,sp3).getOrderInBackTrack()-1);
        numberOfStepsInAStar.set(map1.getCell(fp1,sp1).getOrderInAStar()-1 +
                map2.getCell(fp2,sp2).getOrderInAStar()-1 +
                map3.getCell(fp3,sp3).getOrderInAStar()-1);
    }

    public static void analyzeAllPossibleOutcomes(Map map) {
        boolean usedBE = false;
        boolean usedCBE = false;
        boolean usedBCE = false;
        AtomicInteger numberOfStepsInBacktrackingBE = new AtomicInteger();
        AtomicInteger numberOfStepsInBacktrackingCBE = new AtomicInteger();
        AtomicInteger numberOfStepsInBacktrackingBCE = new AtomicInteger();
        AtomicInteger numberOfStepsInAStarBE = new AtomicInteger();
        AtomicInteger numberOfStepsInAStarCBE = new AtomicInteger();
        AtomicInteger numberOfStepsInAStarBCE = new AtomicInteger();
        Map map1 = new Map();
        Map map2 = new Map();
        Map map3 = new Map();
        Map map4 = new Map();
        Map map5 = new Map();
        Map map6 = new Map();
        Map map7 = new Map();
        Map map8 = new Map();
        map.copyMap(map1);
        map.copyMap(map2);
        map.copyMap(map3);
        map.copyMap(map4);
        map.copyMap(map5);
        map.copyMap(map6);
        map.copyMap(map7);
        map.copyMap(map8);
        map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
        if (map.isBookReachable()) {
            if (map.isExitReachable()) {
                if (map.isCloakReachable()) {//BCE BE CBE
                    //BE
                    calculateWayBE(map1, map2, numberOfStepsInBacktrackingBE, numberOfStepsInAStarBE);
                    usedBE = true;

                    //BCE
                    calculateWayBCE(map3, map4, map5, numberOfStepsInBacktrackingBCE, numberOfStepsInAStarBCE);
                    usedBCE = true;

                    //CBE
                    calculateWayCBE(map6, map7, map8, numberOfStepsInBacktrackingCBE, numberOfStepsInAStarCBE);
                    usedCBE = true;

                } else {//BE
                    //BE
                    calculateWayBE(map1, map2, numberOfStepsInBacktrackingBE, numberOfStepsInAStarBE);
                    usedBE = true;

                }
            } else {
                if (map.isCloakReachable()) {
                    map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
                    map.resetAllReachable();
                    map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
                    if (map.isExitReachable()) {//BCE CBE
                        //BCE
                        calculateWayBCE(map3, map4, map5, numberOfStepsInBacktrackingBCE, numberOfStepsInAStarBCE);
                        usedBCE = true;

                        //CBE
                        calculateWayCBE(map6, map7, map8, numberOfStepsInBacktrackingCBE, numberOfStepsInAStarCBE);
                        usedCBE = true;

                    } else {
                        System.out.println("You Lose!");
                    }
                } else {
                    System.out.println("You Lose!");
                }
            }
        } else if (map.isCloakReachable()) {
            map.activateCloak(map.reachableCloakCoordinate.getFirst(), map.reachableCloakCoordinate.getSecond());
            map.resetAllReachable();
            map.checkAllReachable(map.HarryCoordinate.getFirst(), map.HarryCoordinate.getSecond());
            if (map.isBookReachable()) {

                if (map.isExitReachable()) {//CBE
                    //CBE
                    calculateWayCBE(map6, map7, map8, numberOfStepsInBacktrackingCBE, numberOfStepsInAStarCBE);
                    usedCBE = true;

                } else {
                    System.out.println("You Lose!");
                }
            } else {
                System.out.println("You Lose!");
            }
        } else {
            System.out.println("You Lose!");
        }

        if (usedBCE || usedBE || usedCBE) {
            System.out.println(numberOfStepsInAStarBE+" "+numberOfStepsInBacktrackingBE);
            System.out.println(numberOfStepsInAStarBCE+" "+numberOfStepsInBacktrackingBCE);
            System.out.println(numberOfStepsInAStarCBE+" "+numberOfStepsInBacktrackingCBE);
            int numOfStepsInBacktrackingBE = numberOfStepsInBacktrackingBE.get();
            int numOfStepsInBacktrackingBCE = numberOfStepsInBacktrackingBCE.get();
            int numOfStepsInBacktrackingCBE = numberOfStepsInBacktrackingCBE.get();
            int numOfStepsInAStarBE = numberOfStepsInAStarBE.get();
            int numOfStepsInAStarBCE = numberOfStepsInAStarBCE.get();
            int numOfStepsInAStarCBE = numberOfStepsInAStarCBE.get();

            ArrayList<Integer> bestForBacktracking = new ArrayList<>();
            bestForBacktracking.add(numOfStepsInBacktrackingBE);
            bestForBacktracking.add(numOfStepsInBacktrackingBCE);
            bestForBacktracking.add(numOfStepsInBacktrackingCBE);
            int minBacktrackingVal = 100000;
            int minBacktrackingInd = -1;
            for (int i=0; i<bestForBacktracking.size(); i++) {
                if (bestForBacktracking.get(i)<minBacktrackingVal && bestForBacktracking.get(i)!=0) {
                    minBacktrackingVal = bestForBacktracking.get(i);
                    minBacktrackingInd = i;
                }
            }

            ArrayList<Integer> bestForAStar = new ArrayList<>();
            bestForAStar.add(numOfStepsInAStarBE);
            bestForAStar.add(numOfStepsInAStarBCE);
            bestForAStar.add(numOfStepsInAStarCBE);
            int minAStarVal = 100000;
            int minAStarInd = -1;
            for (int i=0; i<bestForAStar.size(); i++) {
                if (bestForAStar.get(i)<minAStarVal && bestForAStar.get(i)!=0) {
                    minAStarVal = bestForAStar.get(i);
                    minAStarInd = i;
                }
            }

            System.out.println("The shortest way using the Backtracking algorithm takes "+ minBacktrackingVal +" steps.");
            if (minBacktrackingInd == 0) {
                System.out.println("Path would be Harry -- Book -- Exit:");
                System.out.println("Harry -> Book:");
                map1.printMap(1);
                System.out.println("Book -> Exit:");
                map2.printMap(1);
            } else if (minBacktrackingInd == 1){
                System.out.println("Path would be Harry -- Book -- Invisible Cloak -- Exit:");
                System.out.println("Harry -> Book:");
                map3.printMap(1);
                System.out.println("Book -> Invisible Cloak:");
                map4.printMap(1);
                System.out.println("Invisible Cloak -> Exit:");
                map5.printMap(1);
            } else {
                System.out.println("Path would be Harry -- Invisible Cloak -- Book  -- Exit:");
                System.out.println("Harry -> Invisible Cloak:");
                map6.printMap(1);
                System.out.println("Invisible Cloak -> Book:");
                map7.printMap(1);
                System.out.println("Book -> Exit:");
                map8.printMap(1);
            }

            System.out.println("The shortest way using the A* algorithm takes "+ minAStarVal +" steps.");
            if (minAStarInd == 0) {
                System.out.println("Path would be Harry -- Book -- Exit:");
                System.out.println("Harry -> Book:");
                map1.printMap(2);
                System.out.println("Book -> Exit:");
                map2.printMap(2);
            } else if (minAStarInd == 1){
                System.out.println("Path would be Harry -- Book -- Invisible Cloak -- Exit:");
                System.out.println("Harry -> Book:");
                map3.printMap(2);
                System.out.println("Book -> Invisible Cloak:");
                map4.printMap(2);
                System.out.println("Invisible Cloak -> Exit:");
                map5.printMap(2);
            } else {
                System.out.println("Path would be Harry -- Invisible Cloak -- Book  -- Exit:");
                System.out.println("Harry -> Invisible Cloak:");
                map6.printMap(2);
                System.out.println("Invisible Cloak -> Book:");
                map7.printMap(2);
                System.out.println("Book -> Exit:");
                map8.printMap(2);
            }
        }
    }

    public static void startGame() {
        Map map = new Map();
//        map.insertMap(0,0,5,8,2,4,4,0,5,0,7,0);//BE
//        map.insertMap(0,0, 5, 5,1,2,2,4,8,0,0,8);
        map.insertMap(0,0, 6, 3,6,7,8,7,8,0,0,8);//CBE
//        map.insertMap(0,0, 6, 3,6,7,8,7,8,0,8,8);//lose
//        map.checkAllReachable(0,0);
        analyzeAllPossibleOutcomes(map);
    }

    public static void main(String[] args) {
        startGame();

    }
}