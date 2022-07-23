package com.marsh.zutils.property;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class MinioProperties {

    private String endpoint;

    private String accessKey;

    private String accessSecret;
}
