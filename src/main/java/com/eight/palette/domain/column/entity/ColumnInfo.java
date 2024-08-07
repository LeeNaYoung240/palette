package com.eight.palette.domain.column.entity;

import com.eight.palette.domain.board.entity.Board;
import com.eight.palette.domain.card.entity.Card;
import com.eight.palette.domain.column.dto.ColumnInfoRequestDto;
import com.eight.palette.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumnInfo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String statusName;

    @Column
    private Integer position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "columnInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cardList = new ArrayList<>();

    public enum Status {

        ACTIVE,
        DELETED;
    }

    public void delete() {

        this.status = Status.DELETED;
    }

    public void active() {

        this.status = Status.ACTIVE;
    }

    public boolean isActive() {

        return this.status == Status.ACTIVE;
    }

    public void updatePosition(Integer newOrder) {

        this.position = newOrder;
    }

}
