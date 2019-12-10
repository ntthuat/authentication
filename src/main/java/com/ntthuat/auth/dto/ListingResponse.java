package com.ntthuat.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ntthuat
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
// TODO: remove this to common
public class ListingResponse<T> {

    Integer totalPage;

    List<T> data;
}
