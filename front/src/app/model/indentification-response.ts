export interface IndentifitionResponse{
    ghosts:IndentificationResponseGhost[],
    totalScore:number;
}


export interface IndentificationResponseGhost{
    name:string;
    score:number;

}