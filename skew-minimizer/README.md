# Skew Minimizer Identification

Find the location in the sequence of DNA (ie. the genome) that minimizes the skew. If there are multiple minimum values, return all the positions where the skew is minimized.

## Background Information about Skews in Genomes

DNA is composed of a random string of 4 nucelotides (A, T, G, and C) which are expressed in equal amounts in the DNA. However, during the replication process of DNA there are mutations that occur that can change this. An example of this is the hydrolytic deamination of cytosine in the leading strand, this causes on one side of the origin of replication to have more guanine (G) and the other side to have more cysteine (C).

This difference in prevalance of these two nucleotides, allows researchers to determine the point of origin through the following formula:
(G - C) / (G + C).

## Input File

Contains a DNA sequence across multiple lines

## Output File

Contains the index/indices where the skew is minimized and possible location for the point of replication.
