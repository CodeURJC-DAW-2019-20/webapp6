import { Product } from '../product/product.model';

export interface Blog{
    id?:number;
    author:string;
    name:string;
    text:string;
    image:string;
    textfull:string;
    day:number;
    month:number;
    year:number;
    idproduct:number;
    product:Product;

}