/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author jvm
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Write.findAll", query="select w from Writer w"),
    @NamedQuery(name = "Write.findWithParam", query="select w from Writer w where w.code = :fcode")
})

public class Writer extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="WRITER_FK",nullable = true)
    private List<Book> books=new ArrayList<>();
    
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "ADDRESS_FK",nullable = false)
    private Address address;

    public Writer() {
    }

    public Writer(List<Book> books, Address address, String name, String surname, String code) {
        super(name, surname, code);
        this.setBooks(books);
        this.setAddress(address);
    }
    public Writer(Book book, Address address, String name, String surname, String code) {
        super(name, surname, code);
        this.addBook(book);
        this.setAddress(address);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public void addBook(Book book) {
        this.books.add(book);
    }
    public void removeBook(String name){
        for(int i=0;i<this.books.size();i++){
            if(name.equals(this.books.get(i).getName())){
                this.books.remove(i);
            }
        }
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Writer)) {
            return false;
        }
        Writer other = (Writer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder booksStr = new StringBuilder();
        Iterator iter = books.iterator();
        while (iter.hasNext()) {
            booksStr.append(iter.next().toString());
        }
        return "entity.Writer[ id=" + id + " книги=" + booksStr.toString() + " ]";
    }


   

}
