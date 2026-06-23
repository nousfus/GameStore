package com.example.gamestore.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GameRequirements")
public class GameRequirement implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "requirement_id", columnDefinition = "nvarchar(255)")
    private String requirementId;

    // Thiết lập mối quan hệ 1-1 với bảng Game qua cột game_id
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "os", columnDefinition = "nvarchar(255)")
    private String os;

    @Column(name = "processor", columnDefinition = "nvarchar(255)")
    private String processor;

    @Column(name = "memory_ram", columnDefinition = "nvarchar(255)")
    private String memory_ram; 

    @Column(name = "graphicscard", columnDefinition = "nvarchar(255)")
    private String graphicscard;

    @Column(name = "storage", columnDefinition = "nvarchar(255)")
    private String storage;


    // --- CÁC HÀM KHỞI TẠO (CONSTRUCTORS) ---
    public GameRequirement() {
    }

    // --- GETTER & SETTER ---
    public String getRequirementId() { 
        return requirementId; 
    }
    
    public void setRequirementId(String requirementId) { 
        this.requirementId = requirementId; 
    }

    public Game getGame() { 
    	return game; 
    }
    public void setGame(Game game) { 
    	this.game = game; 
    }

    public String getOs() { 
    	return os;
    }
    public void setOs(String os) { 
    	this.os = os; 
    }

    public String getProcessor() { 
    	return processor; 
    }
    public void setProcessor(String processor) { 
    	this.processor = processor; 
    }

    public String getMemory_ram() { 
    	return memory_ram; 
    }
    public void setMemory_ram(String memory_ram) { 
    	this.memory_ram = memory_ram; 
    }

    public String getgraphicscard() { 
    	return graphicscard; 
    }
    public void setgraphicscard(String graphicscard) { 
    	this.graphicscard = graphicscard;
    }

    public String getStorage() {
    	return storage; 
    }
    public void setStorage(String storage) { 
    	this.storage = storage; 
    }


}